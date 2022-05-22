const form = document.getElementById('processForm')

form.addEventListener('submit', (e) => {
     // убирает поведение компонента по дефолту
    e.preventDefault()
    const formData = new FormData(form)
    // получаем значение из импута
    const roomNumber = formData.get('room')
    roomNumber 
     // изначально filter приниммает не только номер аудитории, но и например номер группы или имя преподавателя
    ? fetch('https://time.ulstu.ru/api/1.0/timetable?filter=' + roomNumber)
         // получаем кучу данных от бэка по нажатию кнопки
        .then((response) => response.json()) 
        // затем эти данные идут в data
        .then((data) => {
            // просто посмотреть чего он там получил в строковом формате
            console.log(JSON.stringify(data))
            document.querySelectorAll('#app > *').forEach(data => data.remove()) 
            // получаем кол-во недель из респонса (2) и пошло
            for (const week of data.response.weeks) { 
                renderWeek(week)
            } 
        })
    : alert('А НОМЕР ЗАПОЛНИТЬ ЗАБЫЛИ???')
})

form.addEventListener('reset', (e) => {
    e.preventDefault()
    // удаляем все элементы в элементе с id = 'app'
    document.querySelectorAll('#app > *').forEach(data => data.remove()) 
})

function renderWeek(week) {
    const table = document.createElement('table')
    for (const day of week.days) {
        renderDay(day, table)
    }
    // добавляем  в наш дркумент новую таблицу по одной неделе
    document.getElementById('app').append(table) 
}

function renderDay(day, table) {
    const row = document.createElement('tr')
    for (const lesson of day.lessons) {
        renderLesson(lesson, row)
    }
    // добавляем строки со значениями в таблицу
    table.append(row)
}

function renderLesson(lesson, row) {
    const cell = document.createElement('td')
    if (lesson.length > 0) {
        // устанавливаем значение в dom элеент, 
        // именно тут мы выбираем что выводить в определенный день
        cell.innerHTML = lesson.map(data => data.group).join(', ') //вывести красиво через запятую 
        cell.style = 'background-color: Tomato'
    } else {
        cell.innerHTML = '-'
        cell.style = 'background-color: MediumSeaGreen'
    }
    // добавляем ячейки со значениями в строку
    row.append(cell) 
}

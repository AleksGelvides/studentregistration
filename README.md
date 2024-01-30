# Getting Started

### Конфиги
`app.studentsList.init=true` - Отвечает за автоматическую инициализацию списка студентов. По дефолту автоматическая
инициализаия отключена локально и включена в докере
`app.studentsList.filepath=classpath:/students.json` - файл со списком студентов

### билд скрипт:
`./gradlew bootJar`<br>
`docker-compose build`<br>
`docker-compose up -d` <br>

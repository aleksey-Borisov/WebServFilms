## **Проверка**

Обновление рейтинга и комментриев

```curl.exe -X POST "http://localhost:8080/api/movies/2/rating?rating=4"
```


## **Описание** 

Проект — веб-сервис, который составляет рекомендации фильмов на основе оценок пользователей. API включает в себя следующие типы запросов:

- **GET /movies** — возвращает список всех фильмов, отображая их название, рейтинг и комментарий, если он есть.
- **POST /movies/add** — добавляет новый фильм.
- **GET /recommendations** — возвращает стандартный список фильмов, отсортированный по уменьшению рейтинга.
- **POST /movies/{id}/rating** — устанавливает рейтинг фильма от 1 до 5.
- **GET /recommendations/best** — возвращает стандартный список фильмов с оценкой 5.
- **POST /movies/{id}/comment** — добавляет текстовый комментарий к фильму.

## **Требования к сервису**

- Использовать **Spring Boot** и паттерн **MVC** на основе **Thymeleaf** для обработки запросов.
- Валидировать ответы пользователей, не позволять сохранять данные, некорректные с точки зрения бизнес-логики.
- Приложение не должно крашиться от действий пользователя.
- Хранить отправленные с помощью **POST**-методов данные в рамках жизненного цикла приложения и отображать их при ответах на **GET**-запросы.
- Покрыть проект **unit-тестами** для проверки бизнес-логики.

 

> **Примечание:** заранее подумайте над тем, какие поля должны быть у сущности Movie для реализации всей требуемой бизнес-логики.

## **Тестирование API**

Каждый разработчик отвечает за написание unit-тестов для своей части функционала с использованием **JUnit5 и Mockito**. Тестируйте пограничные кейсы, а не только ожидаемое поведение. Валидируйте полученные данные на уровне бизнес-логики и тестируйте правильность валидации.

Для тестов понадобится зависимость в pom.xml:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```


### Unit-тестирование

Можно писать unit-тесты как обычно с использованием JUnit5.

```javascript
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TruthProviderTest {

    @Test
    void testThatTruthIsTrue() {
        TruthProvider provider = new TruthProvider();

        assertTrue(provider.invoke());
    }
}
```

## **Рабочий процесс команды**

Создайте репозиторий с базовой структурой аналогично семинару.

### **Структура веток** 

- **main** — основная ветка, которая в каждый момент времени должна содержать только работоспособный проект. Считается, что CD (Continuous Deployment) настроен, и в любой момент версию можно зарелизить на пользователей сервиса.
- **Любые другие ветки** создаются от ветки main под разработку конкретного функционала и удаляются после влития MR (Merge Request) в main. 

### **Гайдлайны по ведению разработки**

- Можно создавать по ветке на любую атомарную функциональность, влитие которой не ведёт к потере работоспособности приложения для пользователей. 
- Весь новый функционал вливается только при условии покрытия unit-тестами. 
- Не забывайте использовать автоматическое форматирование кода с помощью сочетания клавиш Option + Command + L. 
- Давайте коммитам осмысленные и краткие названия. Если название получается длинным, то, вероятно, в коммите содержится слишком много функционала.

### **Процесс влития изменений**

- Разработчик убеждается в прохождении всех тестов в проекте с помощью GUI или команды `mvn test`.
- Разработчик создаёт Pull Request.
- Как минимум один другой участник команды проводит код-ревью на предмет плохого стиля кода, использования нежелательных паттернов или неверной бизнес-логики.
- После получения апрува изменения вливаются в master.

### **Финальное тестирование и релиз**

- Запускаются все тесты `mvn test`.
- Проводится ручное тестирование требуемого функционала приложения.


## Статические ресурсы

Добавьте приведённые файлы в папку resources и используйте их в качестве основы для отрисовки View для пользователей. При необходимости файлы можно модифицировать.

### static/style.css

```css
body {
    font-family: Arial, sans-serif;
    margin: 20px;
    padding: 20px;
    background-color: #f4f4f4;
}

table {
    width: 100%;
    border-collapse: collapse;
    background: white;
    border-radius: 8px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    overflow: hidden;
}

th, td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: left;
}

th {
    background-color: #28a745;
    font-weight: bold;
    color: white;
}

tbody tr:nth-child(even) {
    background-color: #f9f9f9;
}

tbody tr:hover {
    background-color: #f1f1f1;
}

label {
    font-weight: bold;
    display: block;
    margin-top: 10px;
}

input, textarea, button {
    width: 95%;
    padding: 8px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

button {
    background-color: #28a745;
    color: white;
    font-size: 16px;
    padding: 10px;
    margin-top: 15px;
    cursor: pointer;
    border: none;
    border-radius: 4px;
}

button:hover {
    background-color: #218838;
}

.container {
    margin-top: 20px;
    max-width: 400px;
    background: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
```


### templates/movies.html

```html
<!DOCTYPE HTML>
<html xmlns:th="https://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="../static/style.css" rel="stylesheet"/>
    <link th:href="@{style.css}" rel="stylesheet" />
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Название</th>
        <th>Рейтинг</th>
        <th>Комментарий</th>
        <th>Обновить рейтинг</th>
        <th>Обновить комментарий</th>
    </tr>
    </thead>
    <tbody>
    <tr th:if="${movies.empty}">
        <td colspan="5">Фильмы не найдены</td>
    </tr>
    <tr th:each="movie : ${movies}">
        <td><span th:text="${movie.name}"></span></td>
        <td><span th:text="${movie.rating}"></span></td>
        <td><span th:text="${movie.comment}"></span></td>
        <td>
            <form th:action="@{'/movies/' + ${movie.id} + '/rating'}" method="post">
                <input type="number" name="rating" min="1" max="10" required>
                <button type="submit">Обновить</button>
            </form>
        </td>
        <td>
            <form th:action="@{'/movies/' + ${movie.id} + '/comment'}" method="post">
                <input type="text" name="comment" th:value="${movie.comment}" required>
                <button type="submit">Обновить</button>
            </form>
        </td>
    </tr>
    </tbody>
</table>

<div class="container">
    <h3>Добавить фильм</h3>
    <form th:action="@{/movies/add}" th:object="${movie}" method="post">
        <label for="name">Название</label>
        <input type="text" id="name" th:field="*{name}" required>

        <label for="rating">Рейтинг от 1 до 5</label>
        <input type="number" id="rating" th:field="*{rating}" required>

        <label for="comment">Комментарий</label>
        <textarea id="comment" th:field="*{comment}"></textarea>

        <button type="submit">Добавить</button>
    </form>
</div>

<div class="container">
    <button onclick="location.href='/movies'">Перейти к фильмам</button>
    <button onclick="location.href='/recommendations'">Перейти к рекомендациям</button>
    <button onclick="location.href='/recommendations/best'">Перейти к лучшим фильмам</button>
</div>
</body>
```

### templates/recommendations.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>Список фильмов</title>
  <link href="../static/style.css" rel="stylesheet"/>
  <link th:href="@{style.css}" rel="stylesheet" />
</head>
<body>
<h2>Рекомендации</h2>
<table>
  <thead>
  <tr>
    <th>Название</th>
    <th>Рейтинг</th>
    <th>Комментарий</th>
  </tr>
  </thead>
  <tbody>
  <tr th:if="${movies.empty}">
    <td colspan="3">Фильмы не найдены</td>
  </tr>
  <tr th:each="movie : ${movies}">
    <td><span th:text="${movie.name}"></span></td>
    <td><span th:text="${movie.rating}"></span></td>
    <td><span th:text="${movie.comment}"></span></td>
  </tr>
  </tbody>
</table>

<div class="container">
  <button onclick="location.href='/movies'">Перейти к фильмам</button>
  <button onclick="location.href='/recommendations'">Перейти к рекомендациям</button>
  <button onclick="location.href='/recommendations/best'">Перейти к лучшим фильмам</button>
</div>
</body>
</html>
```

## Система оценивания

### **1. Реализация GET/POST-запросов**

- **GET-запросы**
  - **0 баллов** — запросы не реализованы.
  - **1 балл** — запросы реализованы, но имеют ошибки или неполную функциональность.
  - **2 балла** — запросы полностью реализованы.
- **POST-запросы**
  - **0 баллов** — запросы не реализованы.
  - **2 балла** — запросы реализованы, но имеют ошибки или неполную функциональность.
  - **4 балла** — запросы полностью реализованы.

### **2. Реализация бизнес-логики**

- **Функциональность рекомендаций с сортировкой по популярности**
  - **0 баллов** — не реализовано.
  - **1 балл** — реализовано с ошибками.
  - **3 балла** — полностью реализовано.
- **Функциональность рекомендаций лучших фильмов**
  - **0 баллов** — не реализовано.
  - **1 балл** — реализовано с ошибками.
  - **3 балла** — полностью реализовано.
- **Функциональность добавления нового фильма**
  - **0 баллов** — не реализовано.
  - **1 балл** — реализовано с ошибками.
  - **3 балла** — полностью реализовано.
- **Функциональность изменения данных о фильмах**
  - **0 баллов** — не реализовано.
  - **1 балл** — реализовано с ошибками.
  - **3 балла** — полностью реализовано.

### **3. Unit-тесты**

- **Покрытие функционала unit-тестами**
  - **0 баллов** — нет unit-тестов.
  - **2 балла** — недостаточное или некорректное покрытие тестами.
  - **4 балла** — все функции покрыты тестами, включая проверку на ошибки и граничные случаи.

### **4. Стандарты чистоты кода**

- **Соответствие стандартам**
  - **0 баллов** — код не соответствует минимальным стандартам чистоты. Не следует принципам SOLID, не отформатирован, названия не в стандартных нотациях, сложно читается, сложно тестируется. 
  - **1 балл** — код соответствует базовым стандартам чистоты. 
  - **2 балла** — код полностью соответствует стандартам.

### **5. Работа с Git и рабочий процесс**

- **Организация работы в Git**
  - **0 баллов** — требования не выполнены, отсутствуют ветки по фичам, нет MR, нет процесса ревью и согласования скоупа работы, большое количество конфликтов и общая неэффективность взаимодействия.
  - **1 балл** — есть нарекания по рабочему процессу.
  - **2 балла** — рабочий процесс организован эффективно.

### Подсчёт баллов

Максимальное количество баллов — 26.

### Конвертация в 10-балльную оценку

| Баллы (из 26) | Оценка (из 10) |
|----|----|
| 25–26 | 10 |
| 22–24 | 9 |
| 19–21 | 8 |
| 17–18 | 7 |
| 15–16 | 6 |
| 12–14 | 5 |
| 10–11 | 4 |
| 7–9 | 3 |
| 4–6 | 2 |
| 1–3 | 1 |
| 0 | 0 |



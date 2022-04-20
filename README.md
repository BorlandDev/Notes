# Notes
Test task

# Simple part

1. Создать пакеты ✔
2. Декомпозировать каждый экран по элементам и задачам ✔
3. Верстка макетов ✔
4. Реализация Базы данных ✔ 
5. Navigation Architecture Component - навигация между фрагментами ✔



# Hard part

1. ПЕРВЫЙ ЗАПУСК ПРИЛОЖЕНИЯ. (Сплеш Скрин с картинкой на весь экран) ✔

2. Открывается лист фрагмент , с 5-ти секундной анимацией (круглый прогресс бар , по центру экрана) , в этот момент мы должны прослушивать доступ к сети,
при его отсутствии или отсутствии данных - отображать надпись по центру “Нет интернета”/ “Нет данных”

Функция будет проверять пуста ли база, если да то запускает круглую анимацию , прослушивает доступ к сети (надпись в случае отсутствия сети), и сохраняет захардкоренные данные в БД, которые отобразятся в ListFragment. Если база не пуста (значит это второй и последующие запуск) то линейная анимация .


3. При включении интернета, запрос должен начаться заново (снова 5 сек)

4. Захардкорить данные для запроса или поместить в JSON-файл и считать/сгенерировать . Код должен обратиться за данными но получить их в течении пяти секунд ✔ 

5. При успешном получении данных - сохраняем их в БД и отображаем в Лист Фрагменте . По умолчанию , при первом запусуке приложения БД пуста ✔


6. ВТОРОЙ И ПОСЛЕДУЮЩИЕ ЗАПУСКИ. (Все тот же Сплеш скрин с картинкой фулл скрин)

7. Открывается Лист Фрагмент . Отображаются данные из БД загруженные ранее ✔

8. Параллельно “обновляем данные с сервера - все те же пять секунд) , анимируем Прогресс бар , тонкая полоса загрузки под ActionBar . Прогресс бар должен отображаться в обоих фрагментах .

9. В это же случае если нет доступа к сети, показываем SnackBar об отсутствии интернета .

10. Запрос не должен прерываться выходом на главный экран кнопкой домой, переключением приложения , или открытием какой-либо заметки из списка.
Он должен прерываться только при закрытии приложения (в onDestroy())




 # Описание тестового задания от работодателя 
**************************************************************************************************
Сделать приложение, в котором на первом экране будет список заметок и кнопка FloatingActionButton. 

(List Fragment) *******

- У каждой заметки есть заголовок, описание и дата изменения (у title и description maxLines = 1, если длиннее, то многоточие в конце, а дата в формате hh:mm для текущего дня и в формате dd.MM.yyyy для предыдущих дней). Всплывает один неочевидный кейс, что в 12 ночи заметки, сделанные сегодня, должны поменять формат ✔

- Можно удалять заметку простым смахиванием вбок ✔

- При нажатии на fab просто добавляется новая заметка в самом верху списка с дефолтным названием «Новая заметка», пустым описанием и текущим временем ✔


(Details Fragment) *******

- При нажатии на один из элементов мы попадем на экран редактирования. Здесь уже полный заголовок и описание. При нажатии на что-либо из этого появляется курсор и можно редактировать текст ✔ 
- Также появляется галочка либо слово «Сохранить» в верхнем правом углу в ActionBar ✔ 
- Если сохраняем текст, то время в списке обновится только в случае измененного текста ✔ 

- По реализации, нужно будет имитировать получение данных с сервера и сохранение в БД. Получаем данные только через 5 сек после отправки запроса ✔ 

- По состояниям экрана со списком:
У нас будет отображаться круглый прогресс бар посередине при первом открытии приложения. Если мы отключаем интернет, то запрос прерывается и отображается текст посередине «Нет интернета», если мы включаем интернет, то запрос должен начаться заново. Если нет данных, то отображаем текст посередине «Нет данных». 


- При успешном получении данных сохраняем их в БД и при следующем запуске приложения мы отображаем данные из БД и параллельно обновляем данные с сервера. Для пользователя это будет видно как горизонтальный прогресс бар (тонкая полоса загрузки) прямо под ActionBar. В этом же случае при прерывании запроса отключением интернета просто показываем Snackbar об отсутствии интернета. Запрос не должен прерываться выходом на главный экран кнопкой домой, переключением приложения или открытием какой-либо заметки из списка. Он должен прерваться только если мы полностью вышли из приложения.

- Экран списка заметок и экран деталей заметки должны быть фрагментами и поэтому реализацию переходов следует сделать с помощью графа (Navigation Architecture Component) ✔


# Notes
Test task


1. Создать пакеты ✔
2. Декомпозировать каждый экран по элементам и задачам ✔
3. Верстка макетов ✔
4. Реализация Базы данных ✔ 
5. Navigation Architecture Component - навигация между фрагментами ✔
6. Класс для имитации получения данных с сервера и записи их в БД (задержка 5 секунд)
7. BroadCast Receiver , для прослушки доступности сети и вывода анимации , так же реацкию на отсутсвие сети .
8. Реализация: при усппешном запросе к серверу , сохраняем данные в БД.


Реализация первого запуска приложения .
Реализация последующих запусков приложения .
Реализация имитации загрузки данных из сети и обработки событий (Асинхронно приостановка потока с анимацией прогресс бара , прослушка интернет-соединения через BroadCast Receiver) .

Сымитировать загрузку из сети , данные можно захардкорить/прописать в JSON-файл и считать/ сгенерировать.
Код должен обратится за данными но получить их в течении 5ти секунд . После завершения , если данные успешно получены - записать их в БД. При последующем запуске приложение сначала берет данные с БД, потом отправляет запрос на данные из сети .

Тоесть по-умолчанию данных в БД нет , она пустая .

По реализации, нужно будет имитировать получение данных с сервера и сохранение в БД. Получаем данные только через 5 сек после отправки запроса.

- По состояниям экрана со списком:
У нас будет отображаться круглый прогресс бар посередине при первом открытии приложения. Если мы отключаем интернет, то запрос прерывается и отображается текст посередине «Нет интернета», если мы включаем интернет, то запрос должен начаться заново. Если нет данных, то отображаем текст посередине «Нет данных». 


- При успешном получении данных сохраняем их в БД и при следующем запуске приложения мы отображаем данные из БД и параллельно обновляем данные с сервера. Для пользователя это будет видно как горизонтальный прогресс бар (тонкая полоса загрузки) прямо под ActionBar. В этом же случае при прерывании запроса отключением интернета просто показываем Snackbar об отсутствии интернета. Запрос не должен прерываться выходом на главный экран кнопкой домой, переключением приложения или открытием какой-либо заметки из списка. Он должен прерваться только если мы полностью вышли из приложения.





 
**************************************************************************************************
Сделать приложение, в котором на первом экране будет список заметок и кнопка FloatingActionButton. 

(List Fragment) *******

- У каждой заметки есть заголовок, описание и дата изменения (у title и description maxLines = 1, если длиннее, то многоточие в конце, а дата в формате hh:mm для текущего дня и в формате dd.MM.yyyy для предыдущих дней). Всплывает один неочевидный кейс, что в 12 ночи заметки, сделанные сегодня, должны поменять формат ✔

- Можно удалять заметку простым смахиванием вбок ✔

- При нажатии на fab просто добавляется новая заметка в самом верху списка с дефолтным названием «Новая заметка», пустым описанием и текущим временем ✔


(Details Fragment) *******

- При нажатии на один из элементов мы попадем на экран редактирования. Здесь уже полный заголовок и описание. При нажатии на что-либо из этого появляется курсор и можно редактировать текст. Также появляется галочка либо слово «Сохранить» в верхнем правом углу в ActionBar ✔ 
- Если сохраняем текст, то время в списке обновится только в случае измененного текста ✔ (в моем случае галочка "сохранить" видна всегда)

- По реализации, нужно будет имитировать получение данных с сервера и сохранение в БД. Получаем данные только через 5 сек после отправки запроса.

- По состояниям экрана со списком:
У нас будет отображаться круглый прогресс бар посередине при первом открытии приложения. Если мы отключаем интернет, то запрос прерывается и отображается текст посередине «Нет интернета», если мы включаем интернет, то запрос должен начаться заново. Если нет данных, то отображаем текст посередине «Нет данных». 


- При успешном получении данных сохраняем их в БД и при следующем запуске приложения мы отображаем данные из БД и параллельно обновляем данные с сервера. Для пользователя это будет видно как горизонтальный прогресс бар (тонкая полоса загрузки) прямо под ActionBar. В этом же случае при прерывании запроса отключением интернета просто показываем Snackbar об отсутствии интернета. Запрос не должен прерываться выходом на главный экран кнопкой домой, переключением приложения или открытием какой-либо заметки из списка. Он должен прерваться только если мы полностью вышли из приложения.

- Экран списка заметок и экран деталей заметки должны быть фрагментами и поэтому реализацию переходов следует сделать с помощью графа (Navigation Architecture Component) ✔


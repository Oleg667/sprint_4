QuestionsTest -тест вопросов на главной странице
TestOrder - тест кнопок "Заказать"
TestOrderButton - тест флоу позитивного сценария с перебором всех возможных сроков заказа

По умолчанию работает через Ghrom
Для запуска через Fierfox: -Dbrowser=firefox
mvn clean test -Dbrowser=firefox

Для запуска одного тестового файла: -Dtest= <Имя файла>
mvn clean test -Dtest=TestOrder

Тест позитивного сценария на Ghrom падает в связи с неактивной кнопкой
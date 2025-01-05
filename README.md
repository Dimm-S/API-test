## Пример автотестов API сайта pokemonbattle.ru

- TrainerTest - проверка id и имени тренера.
- CreatePokemonTest - создание покемона, переименование и помещение в покебол.
### Запуск в docker
1. В dockerfile прописать значения id, токена и имени своего тренера.
2. Собрать образ
`docker build -t api-test .`
3. Запустить контейнер `docker run --name test -p 4444:4444 api-test`
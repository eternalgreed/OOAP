> Составьте предварительное, самое общее, словесное описание системы, которую вы хотите сделать. Придерживаясь принципов модульности, определите в ней 5-7 наиболее общих сущностей (потенциальных АТД), пока кратко и неформально их опишите.Укажите, что делает в системе каждая такая сущность, стараясь, чтобы из этого множества формулировок "кто что делает" была бы хорошо понятна и общая идея работы системы в целом. 


## Игра :
- Центральная сущность, инициализирующая остальные, внутри происходит главный цикл

## Поле :
-  Игрвое поле, включающее в себя набор клеток, имеет  размеры

## Ячейка :
- Клетка на игровом поле, может содержать в себе игровой элемент

## Игровой элемент :
- Простое содержимое ячейки, имеет тип (буквенное обозначение)

## Игрок :
- Актор, также имеет лог своих действий

## Правила :
- Объект содержащий в себе правила игры (условия при котором собирается ряд, условия начисление бонусов)

## Исполнитель :
- Объект, отвечающий за исполнение игры - анализ ситуации на поле и применение действий согласно правилам   
﻿# Учебное задание "Проект Шахматы (HW-02)"
> Давайте напишем шахматы. Для начала надо спроектировать нашу игру.

Самое главное в шахматах — это фигуры, поэтому для начала нам надо будет сделать абстрактный класс фигуры (ChessPiece), чтобы в будущем легко написать все типы фигур в игре. Но это ещё не всё, нам надо куда-то ставить фигуры, для этого нам нужен класс доски (ChessBoard), который будет отвечать за управление всей игрой. Эти шахматы будут максимально простыми, с консольным простеньким интерфейсом, но больше сейчас нам и не нужно.

Чтобы проверить игру надо вводить в консоль команды:
- 'exit' - для выхода
- 'replay' - для перезапуска игры
- 'castling0' или 'castling7' - для рокировки по соответствующей линии
- 'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3(поле это двумерный массив от 0 до 7)

>Проверьте могут ли фигуры ходить друг сквозь друга, корректно ли съедают друг друга, можно ли поставить шах и сделать рокировку?

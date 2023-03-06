<h1>Профилирование трекера</h1>
<p><b>Параметры виртуальной машины:</b> -XX:+UseParallelGC -Xmx500m -Xms500m</p>
<h4>Используемые инструменты:</h4>
<ul>
    <li>профилировщик Visual VM</li>
    <li>плагин Visual GC</li>
</ul>
<h4>Анализ:</h4>
<p>При добавлении большого количества объектов (например: при заданных ограничениях 3млн объектов типа Item), не помещающихся в памяти кучи программа завершается с ошибкой OutOfMemoryError. Это видно на вкладке Monitor на графике Heap (рис 1). А так же на вкладке Visual GC мы видим переполнение всех регионов кучи (рис 2, рис 3).</p>
<figure>
    <img src="./images/1.jpg" title="1">
    <figcaption>Рис 1. Переполнение кучи на Monitor -> Heap</figcaption>
</figure>
<figure>
    <img src="./images/2.jpg" title="2">
    <figcaption>Рис 2. Переполнение кучи в Visual GC</figcaption>
</figure>
<figure>
    <img src="./images/3.jpg" title="3">
    <figcaption>Рис 3. Переполнение кучи в Visual GC</figcaption>
</figure>
<p>Посмотреть содержимое кучи в конкретный момент времени мы можем с помощью Heap Dump раздел instances by Size. В нашем случае видим следующее:</p>
<figure>
    <img src="./images/4.jpg" title="4">
    <figcaption>Рис 4. instances by Size</figcaption>
</figure>
<figure>
    <img src="./images/5.jpg" title="5">
    <figcaption>Рис 5. Подробная информация об объектах</figcaption>
</figure>
<p>При удалении всех объектов, активно задействуется ресурс процессора. Это особенно заметно при удалении большого числа объектов, например 1млн. Это заметно на вкладке Monitor на графике CPU (рис 6). Подробно изучить какие методы потребляют больше всего времени и ресурсов CPU можно на вкладке Sampler -> CPU -> CPU samples (рис 7), где можно сделать snapshot в момент максимальной нагрузки.</p>
<figure>
    <img src="./images/6.jpg" title="6">
    <figcaption>Рис 6. Monitor -> CPU</figcaption>
</figure>
<figure>
    <img src="./images/7.jpg" title="7">
    <figcaption>Рис 7. instances by Size</figcaption>
</figure>
<p>Показ всех заявок, так же активно задействуется ресурс процессора, но выполняется значительно быстрее (рис 8).</p>
<figure>
    <img src="./images/8.jpg" title="8">
    <figcaption>Рис 8. Monitor -> CPU</figcaption>
</figure>
<p>Исходя из анализа можно сделать вывод, что слабое место программы это удаление всех заявок. Метод execute имеет сложность O(n^2) (проходится по всем элементам списка O(n) и на каждой итерации удаляет заявку с начала списка O(n)). Для оптимизации можно реализовать в MemTracker метод deleteAll, который будет для списка заявок выполнять метод List.clear().</p>
<p>Показ всех заявок выполняется значительно быстрее, т.к. операция имеет линейную сложность выполнения.</p>
<h4>Сборка мусора:</h4>
С помощью плагина Visual GC можно отслеживать малые и полные сборки мусора на следующемграфике:
<figure>
    <img src="./images/9.jpg" title="9">
    <figcaption>Рис 9. Visual GC</figcaption>
</figure>
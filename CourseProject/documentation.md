Складской учёт 
========================= 
# Введение 

Требуется создать рабочий прототип Web приложения для работы с пользователями их товарами.  
Приложение должно реализовывать следующие функции: 
* Просмотр списка товаров и редактирование этого списка 
* Отображение количества товаров в системе 
* Просмотр и редактирование всех категорий товаров 
* Отображения количества товаров в данной категории 

# 1. Категории 
### 1.1 Просмотр списка категорий: 
Данный режим программы предназначен для просмотра списка категорий. 

##### Основной сценарий: 
* Пользователь выбирает пункт меню "Категории". 
* Отображается форма просмотра списка всех Категории.  
![228.png](https://www.dropbox.com/s/n8zwo55txoir48c/228.png?dl=0&raw=1)  
Рисунок 1.1 Просмотр списка Категорий 

В списке отображаются следующие колонки: 
* Категории - имя категории в системе; 
* Подкатегории - подкатегории выбранной категории; 
* Количество товаров - количество товаров в данной категории в системе. 

### 1.2 Добавления категории 
##### Основной сценарий: 
* Пользователь, находясь в режиме просмотра списка категорий, нажимает кнопку "Добавить"; 
* Отображается форма добавления новой категории; 
* Пользователь вводит данные и нажимает кнопку "Добавить"; 
* Если данные введены не корректно, то пользователю отображается соответствующие сообщение; 
* Если данные корректны, то категория добавляется в базу данных; 
* Если произошла ошибка сохранения данных, то выводится сообщение об ошибке; 
* Если категория успешно добавлена, то открывается форма просмотра списка категорий с обновленными данными. 

##### Сценарий отмены изменений: 
* Пользователь, находясь в режиме просмотра списка категорий нажимает кнопку "Добавить"; 
* Отображается форма добавления категории; 
* Пользователь вводит данные и нажимает кнопку "Назад"; 
* Данные не сохраняются в базу данных и открывается форма просмотра списка категорий с обновлёнными данными.  
![Img21.png](https://www.dropbox.com/s/6zkgywl8p7z3ejw/Img21.png?dl=0&raw=1)  
Рисунок 1.2 - Добавление категории 

При добавлении категории вводятся следующие реквизиты: 
* Название категории; 
* Выбирается родительская категория или создаётся самостоятельная категория. 

### 1.3 Редактирование категории 
##### Основной сценарий: 
* Пользователь, находясь в режиме просмотра списка категорий, нажимает кнопку "Редактировать" в строке выбранной категории; 
* Отображается форма редактирование; 
* Пользователь изменяет данные и нажимает кнопку "Сохранить"; 
* Если данные введены не корректно, то пользователю отображается соответствующие сообщение; 
* Если данные корректны, то происходит обновление в базе данных; 
* Если произошла ошибка сохранения данных, то выводится сообщение об ошибке; 
* Если категория успешно обновлена, то открывается форма просмотра списка категорий с обновленными данными. 

##### Сценарий отмены изменений: 
* Пользователь, находясь в режиме просмотра списка категорий, нажимает кнопку "Редактировать" в строке выбранной категории; 
* Отображается форма редактирования категории; 
* Пользователь вводит данные и нажимает кнопку "Назад"; 
* Данные не сохраняются в базу данных и открывается форма просмотра списка категорий с обновлёнными данными.  
![img10.png](https://www.dropbox.com/s/iwi1dbe19hysqwc/img10.png?dl=0&raw=1)  
1.3 Редактирование категории 

##### Ограничение данных: 
Название категории - содержит не более 32 символов. 

### 1.4 Удаление категории 
##### Основной сценарий: 
* Пользователь, находясь в режиме просмотра списка категорий, нажимает кнопку "Удалить" в строке выбранной роли; 
* Происходит проверка на возможность удаления (есть ли в данной категории товары и имеет ли эта категория дочерние); 
* Если данную категорию нельзя удалить, то выводиться соответствующее сообщение; 
* Если категорию можно удалить, то пользователя просят подтвердить действие; 
* Пользователь нажимает кнопку "Да"; 
* Происходит удаление категории в базе данных; 
* Если произошла ошибка при удалении, то выводится соответствующее сообщение; 
* Если категория удалена успешно, то открывается форма просмотра категорий с обновлёнными данными. 

##### Сценарий отмены удаления: 
* Пользователь, находясь в режиме просмотра списка категорий нажимает кнопку "Удалить" в строке выбранной категории; 
* Отображается диалоговое окно; 
* Пользователь нажимает кнопку "Нет"; 
* Открывается форма просмотра категорий с обновлёнными данными.  
![img7.png](https://www.dropbox.com/s/ig820kk8lllk7n9/img7.png?dl=0&raw=1)  
1.4 Подтверждение удаления роли 

# 2.Товары 
### 2.1 Просмотр списка товаров 
##### Основной сценарий 
* Пользователь выбирает пункт меню "Товары" 
* Отображается форма просмотра списка всех товаров  
![img18.png](https://www.dropbox.com/s/tsg2jlga5675pzq/img18.png?dl=0&raw=1)  
2.1 Список всех товаров 

В списке отображаются следующие колонки: 
* Количество наименований - количество уникальных наименований в системе; 
* Товаров всего - общее количество товаров в системе; 
* Категория - категория товара; 
* Подкатегория - подкатегория това; 
* Дата добавления - дата добавления товара в систему; 
* Количество - количество добавленыых товаров в системе. 

##### Фильтрация 
Для просмотра списка товаров, которые были добавлены за определенный период предусмотрена возможность выбора периода отбора данных с и по. 
Начальная дата фильтра не должна быть больше конечной. 
Если данные не введены, то фильтрация не осуществляется. 
Если не введено значение начальной даты, то фильтрация только по конечной дате. 
Если не введена конечная дата, то фильтрация только по 
начальной дате. 
Фильтрация по умолчанию выставляется с 1-го числа текущего месяца и по текущую дату. 

Для просмотра списка всех товаров из какой-либо категории нужно выбрать название категории в соответсвующем разделе фильтрации. 
По умолчания показываются товары из всех категорий. 

Если выбранны оба фильтра, то список товаров будет создаваться на основе объединения правил фильтрации обоих фильтров. 

Обновление данных после выбора данных фильтрации осуществляется нажатием кнопки "Обновить". 

### 2.2 Добавление товара 
##### Основной сценарий: 
* Пользователь, находясь в режиме просмотра списка товаров, нажимает кнопку "Добавить"; 
* Отображается форма добавления нового товара; 
* Пользователь вводит данные и нажимает "Добавить"; 
* Если данные введены не корректно, то пользователю отображается соответствующие сообщение; 
* Если данные корректны, то товар добавляется в базу данных; 
* Если произошла ошибка сохранения данных, то выводится сообщение об ошибке; 
* Если товар успешно добавлен, то открывается форма просмотра списка категорий с обновленными данными. 

##### Сценарий отмены изменений: 
* Пользователь, находясь в режиме просмотра списка товаров, нажимает кнопку "Добавить"; 
* Отображается форма добавления товара; 
* Пользователь вводит данные и нажимает кнопку "Назад"; 
* Данные не сохраняются в базу данных и открывается форма просмотра списка товаров с обновлёнными данными.  
![Img17.png](https://www.dropbox.com/s/q3dlz03gs8z153y/Img17.png?dl=0&raw=1)  
2.2 Форма добавления товара 

При добавлении товара вводятся следующие реквизиты: 
* Название товара - название товара в системе; 
* Количество товаров - количество данного наименование; 
* Категория товара - выбирается категория товара из всех подкатегорий, находящихся в системе. Общая категория присваивается автоматически. 

### 2.3 Редактирование товара 
##### Основной сценарий: 
* Пользователь, находясь в режиме просмотра списка товаров, нажимает кнопку "Редактировать" в строке выбранного товара; 
* Отображается форма редактирование; 
* Пользователь изменяет данные и нажимает кнопку "Изменить"; 
* Если данные введены не корректно, то пользователю отображается соответствующие сообщение; 
* Если данные корректны, то происходит обновление в базе данных; 
* Если произошла ошибка сохранения данных, то выводится сообщение об ошибке; 
* Если товар успешно обновлён, то открывается форма просмотра списка товаров с обновленными данными. 

##### Сценарий отмены изменений: 
* Пользователь, находясь в режиме просмотра списка товаров, нажимает кнопку "Редактировать" в строке выбранного товара; 
* Отображается форма
Посмотреть все изображения
 
редактирования товара; 
* Пользователь вводит данные и нажимает кнопку "Назад"; 
* Данные не сохраняются в базу данных и открывается форма просмотра списка товаров с обновлёнными данными.  
![img20.png](https://www.dropbox.com/s/xkpnuhf0rmun0zw/img20.png?dl=0&raw=1)  
2.3 Редактирование товара 

При редактировании товара вводятся следующие реквизиты: 
* Название товара - название товара в системе; 
* Количество товаров - количество данного наименование; 
* Категория товара - выбирается категория товара из всех подкатегорий, находящихся в системе. Общая категория присваивается автоматически. 

### 2.4 Удаление товара 
##### Основной сценарий: 
* Пользователь, находясь в режиме просмотра списка товаров, нажимает кнопку "Удалить" в строке выбранного товара; 
* Отображается диалог подтверждения на удаление; 
* Пользователь нажимает кнопку "Да"; 
* Происходит удаление товара в базе данных; 
* Если произошла ошибка при удалении, то выводится соответствующее сообщение; 
* Если товар удалён успешно, то открывается форма просмотра товаров с обновлёнными данными. 

##### Сценарий отмены удаления: 
* Пользователь, находясь в режиме просмотра списка товаров, нажимает кнопку "Удалить" в строке выбранного товара; 
* Отображается диалоговое окно; 
* Пользователь нажимает кнопку "Нет"; 
* Открывается форма просмотра товаров с обновлёнными данными.  
![img22.png](https://www.dropbox.com/s/mce6mc6b72bm09p/img22.png?dl=0&raw=1)  
2.4 Удаление товара
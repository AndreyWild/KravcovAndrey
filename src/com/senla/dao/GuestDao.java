package com.senla.dao;

import com.senla.api.dao.IGuestDao;
import com.senla.model.Guest;


public class GuestDao extends AbstractDao<Guest> implements IGuestDao { // Реализация интерфейса IGuestDao

    // Большинство методов реализовано в AbstractDao

    //    private List<Guest> guests = new ArrayList<>(); // Список гостей, замена базы данных
//
//    /**
//     * Метод сохраняет Гостя в БД (массиве)
//     * @param entity объект Guest
//     */
//    @Override
//    public void save(Guest entity) {
//        entity.setId(IdGenerator.generateGuestId()); // генерируем и присвиваем Id
//        guests.add(entity); // добавляем пользователя в массив
//
//    }
//
//    /**
//     * Метод возвращает Гостя по заданному id
//     * @param id гостя
//     * @return Guest
//     */
//    @Override
//    public Guest getById(Long id) {
//        return guests.stream() // стрим из массива
//                .filter(x -> id.equals(x.getId())) // фильтрация по id
//                .findFirst() // вернуть первый элемент коллекции
//                .orElse(null); // или 0, если коллекция пуста
//    }
//
//    /**
//     * Метод возвращаем список Гостей
//     * @return List<Guest>
//     */
//    @Override
//    public List<Guest> getAll() {
//        return new ArrayList<>(guests);
//    }
//
//    /**
//     * Метод удаляет гостя
//     * @param entity объект Guest
//     */
//    @Override
//    public void delete(Guest entity) {
//        guests.remove(entity);
//    }
//
//    /**
//     * Метод изменяет данные Гостя
//     * @param entity объект Guest
//     * @return измененного гостя
//     */
    @Override
    public Guest update(Guest entity) {
        Guest guest = getById(entity.getId()); // создаем новый объект Гость и присваиваем ему id (из рпараметра)
        guest.setName(entity.getName()); // меняем ему имя (из параметра)
        guest.setAge(entity.getAge()); // и возраст
        return guest;
    }
}

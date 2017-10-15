package synchronization.locks.entities;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import synchronization.locks.utils.Logger;

import java.util.Random;

/**
 * Класс людей, которые будут жить в домах
 */
@Getter                 // сгенерировать геттеры для всех полей
@EqualsAndHashCode      // сгенерировать equals() и hashCode()
public class Person implements Runnable, Comparable<Person> {
    // мужские имена
    @Getter(AccessLevel.NONE)
    private static final String[] MALE_NAMES = {"Абрам", "Аваз", "Августин", "Авраам", "Агап", "Агапит",
            "Агафон", "Адам", "Адриан", "Азамат", "Азат", "Айдар", "Айрат", "Акакий", "Аким", "Алан",
            "Александр", "Алексей", "Али", "Алик", "Алихан", "Алмаз", "Альберт", "Амир", "Амирам",
            "Анар", "Анастасий", "Анатолий", "Ангел", "Андрей", "Анзор", "Антон", "Анфим", "Арам",
            "Аристарх", "Аркадий", "Арман", "Армен", "Арсен", "Арсений", "Арслан", "Артемий", "Артур",
            "Артём", "Архип", "Аскар", "Асхан", "Ахмет", "Ашот", "Бахрам", "Блез", "Богдан", "Борис",
            "Борислав", "Бронислав", "Булат", "Вадим", "Валентин", "Валерий", "Вальдемар", "Вардан",
            "Василий", "Вениамин", "Виктор", "Вильгельм", "Вит", "Виталий", "Владимир", "Владислав",
            "Владлен", "Влас", "Всеволод", "Вячеслав", "Гавриил", "Гамлет", "Гарри", "Геннадий", "Генри",
            "Генрих", "Георгий", "Герасим", "Герман", "Германн", "Глеб", "Гордей", "Григорий", "Густав",
            "Давид", "Давлат", "Дамир", "Дана", "Даниил", "Данислав", "Данияр", "Дарий", "Даурен",
            "Демид", "Демьян", "Денис", "Джамал", "Джеймс", "Джереми", "Джером", "Джозеф", "Джонатан",
            "Дик", "Динар", "Дино", "Дмитрий", "Добрыня", "Доминик", "Евгений", "Евдоким", "Евсей",
            "Евстахий", "Егор", "Елисей", "Емельян", "Еремей", "Ефим", "Ефрем", "Ждан", "Жерар", "Жигер",
            "Закир", "Заур", "Захар", "Зенон", "Зигмунд", "Зиновий", "Зураб", "Зуфар", "Ибрагим", "Иван",
            "Игнат", "Игнатий", "Игорь", "Иеремия", "Иероним", "Иисус", "Ильгиз", "Ильнур", "Ильшат",
            "Илья", "Имран", "Иннокентий", "Иосиф", "Ираклий", "Исаак", "Исаакий", "Исидор", "Искандер",
            "Ислам", "Исмаил", "Итан", "Казбек", "Камиль", "Карен", "Карим", "Карл", "Ким", "Кир",
            "Кирилл", "Клаус", "Клим", "Конрад", "Константин", "Корнелий", "Кристиан", "Кузьма",
            "Лаврентий", "Лев", "Ленар", "Леон", "Леонард", "Леонид", "Леопольд", "Лоренс", "Лука",
            "Лукиллиан", "Лукьян", "Любомир", "Людвиг", "Людовик", "Люций", "Маджид", "Майкл", "Макар",
            "Макарий", "Максим", "Максимилиан", "Максуд", "Мансур", "Мар", "Марат", "Марк", "Марсель",
            "Мартин", "Мартын", "Матвей", "Махмуд", "Мика", "Микула", "Милослав", "Мирон", "Мирослав",
            "Михаил", "Моисей", "Мстислав", "Мурат", "Муслим", "Мухаммед", "Мэтью", "Назар", "Наиль",
            "Нестор", "Никита", "Никодим", "Никола", "Николай", "Нильс", "Огюст", "Олег", "Оливер",
            "Орест", "Орландо", "Осип", "Оскар", "Остап", "Остин", "Павел", "Панкрат", "Патрик", "Педро",
            "Перри", "Платон", "Потап", "Прохор", "Пётр", "Равиль", "Радий", "Радик", "Радомир",
            "Радослав", "Разиль", "Райан", "Раймонд", "Рамазан", "Рамиз", "Рамиль", "Рамон", "Ранель",
            "Расим", "Расул", "Ратмир", "Рафаэль", "Рафик", "Рашид", "Ренат", "Ринат", "Ричард",
            "Роберт", "Родион", "Ролан", "Роман", "Ростислав", "Рубен", "Рудольф", "Руслан", "Рустам",
            "Рэй", "Савва", "Савелий", "Саид", "Самат", "Самвел", "Самир", "Самуил", "Санжар", "Сани",
            "Святослав", "Севастьян", "Семён", "Серафим", "Сергей", "Сесил", "Сидор", "Спартак",
            "Станислав", "Степан", "Султан", "Тагир", "Тайлер", "Тамаз", "Тамерлан", "Тарас", "Тигран",
            "Тимофей", "Тимур", "Тихон", "Томас", "Трофим", "Уинслоу", "Умар", "Устин", "Фазиль",
            "Фарид", "Федот", "Феликс", "Филипп", "Флор", "Фома", "Фред", "Фридрих", "Фёдор", "Хабиб",
            "Хаким", "Харитон", "Хасан", "Цезарь", "Цефас", "Цецилий", "Цицерон", "Чарльз", "Чеслав",
            "Чингиз", "Шамиль", "Шарль", "Шерлок", "Эдгар", "Эдуард", "Эльдар", "Эмиль", "Эмин", "Эрик",
            "Эркюль", "Эрмин", "Эузебио", "Юлиан", "Юлий", "Юнус", "Юрий", "Юстиниан", "Юстус", "Яков",
            "Ян", "Яромир", "Ярослав"};
    // женские имена
    @Getter(AccessLevel.NONE)
    private static final String[] FEMALE_NAMES = {"Августа", "Августина", "Авдотья", "Аврора", "Агапия",
            "Агата", "Агафья", "Аглая", "Агния", "Агунда", "Ада", "Аделаида", "Аделина", "Адель",
            "Адиля", "Адриана", "Аза", "Азалия", "Азиза", "Аида", "Аиша", "Айгерим", "Айгуль", "Айлин",
            "Айнагуль", "Айнур", "Айсель", "Айсун", "Айсылу", "Аксинья", "Алана", "Алевтина",
            "Александра", "Алеста", "Алина", "Алиса", "Алия", "Алла", "Алсу", "Альба", "Альбина",
            "Альфия", "Аля", "Алёна", "Амалия", "Амина", "Амира", "Анаит", "Анастасия", "Ангелина",
            "Анжела", "Анжелика", "Анисья", "Анна", "Антонина", "Анфиса", "Аполлинария", "Арабелла",
            "Ариана", "Арина", "Асель", "Асия", "Астрид", "Ася", "Аэлита", "Бажена", "Беатрис", "Бела",
            "Белинда", "Белла", "Берта", "Богдана", "Божена", "Бэлла", "Валентина", "Валерия", "Ванда",
            "Ванесса", "Варвара", "Василина", "Василиса", "Венера", "Вера", "Вероника", "Веста", "Вета",
            "Викторина", "Виктория", "Вилена", "Виола", "Виолетта", "Вита", "Виталина", "Виталия",
            "Влада", "Владана", "Владислава", "Габриэлла", "Галина", "Галия", "Генриетта", "Гоар",
            "Грета", "Гульзира", "Гульмира", "Гульназ", "Гульнара", "Гульшат", "Гюзель", "Далида",
            "Дамира", "Дана", "Даниэла", "Дания", "Дара", "Дарина", "Дарья", "Даяна", "Джамиля",
            "Дженна", "Дженнифер", "Джессика", "Джиневра", "Диана", "Дильназ", "Дильнара", "Диля",
            "Дилярам", "Дина", "Динара", "Долорес", "Доминика", "Домна", "Домника", "Ева", "Евангелина",
            "Евгения", "Евдокия", "Екатерина", "Елена", "Елизавета", "Есения", "Жаклин", "Жанна",
            "Жансая", "Жасмин", "Жозефина", "Жоржина", "Забава", "Заира", "Залина", "Замира", "Зара",
            "Зарема", "Зарина", "Земфира", "Зинаида", "Зита", "Злата", "Златослава", "Зоряна", "Зоя",
            "Зульфия", "Зухра", "Ивета", "Иветта", "Изабелла", "Иллирика", "Илона", "Ильзира", "Илюза",
            "Инга", "Индира", "Инесса", "Инна", "Иоанна", "Ира", "Ирада", "Ираида", "Ирина", "Ирма",
            "Искра", "Ия", "Камила", "Камилла", "Кара", "Карина", "Каролина", "Кира", "Клавдия",
            "Клара", "Кора", "Корнелия", "Кристина", "Ксения", "Лада", "Лана", "Лара", "Лариса",
            "Лаура", "Лейла", "Леона", "Лера", "Леся", "Лиана", "Лидия", "Лика", "Лили", "Лилиана",
            "Лилия", "Лина", "Линда", "Лиора", "Лира", "Лия", "Лола", "Лолита", "Лора", "Луиза",
            "Лукерья", "Лукия", "Любава", "Любовь", "Людмила", "Люсиль", "Люсьена", "Люция", "Люче",
            "Ляйсан", "Ляля", "Мавиле", "Мавлюда", "Магда", "Магдалeна", "Мадина", "Мадлен", "Майя",
            "Макария", "Малика", "Мара", "Маргарита", "Марианна", "Марика", "Марина", "Мария", "Мариям",
            "Марта", "Марфа", "Мелания", "Мелисса", "Мика", "Мила", "Милада", "Милана", "Милен",
            "Милена", "Милица", "Милослава", "Мира", "Мирослава", "Мирра", "Мия", "Моника", "Муза",
            "Надежда", "Наиля", "Наима", "Нана", "Наоми", "Наргиза", "Наталья", "Нелли", "Нея", "Ника",
            "Николь", "Нина", "Нинель", "Номина", "Нора", "Нурия", "Одетта", "Оксана", "Октябрина",
            "Олеся", "Оливия", "Ольга", "Офелия", "Павлина", "Патриция", "Паула", "Пейтон", "Пелагея",
            "Перизат", "Платонида", "Полина", "Прасковья", "Рада", "Разина", "Раиса", "Рамина", "Регина",
            "Резеда", "Рена", "Рената", "Риана", "Рианна", "Рикарда", "Римма", "Рина", "Рита", "Рогнеда",
            "Роза", "Рузалия", "Рузанна", "Русалина", "Руслана", "Руфина", "Руфь", "Сабина", "Сабрина",
            "Сажида", "Саида", "Самира", "Сандра", "Сания", "Сара", "Сати", "Сафия", "Сафура",
            "Светлана", "Севара", "Серафима", "Сесилия", "Сиара", "Сильвия", "Снежана", "Соня", "Софья",
            "Стелла", "Стефания", "Сусанна", "Таисия", "Тамара", "Тамила", "Тара", "Татьяна", "Таяна",
            "Теона", "Тереза", "Тина", "Томирис", "Тора", "Ульяна", "Урсула", "Устинья", "Фазиля",
            "Фаина", "Фарида", "Фариза", "Фатима", "Феруза", "Фируза", "Флора", "Флоренс", "Флорентина",
            "Флоренция", "Флориана", "Фредерика", "Фрида", "Фёкла", "Хадия", "Хилари", "Хлоя", "Цагана",
            "Цветана", "Цецилия", "Циара", "Челси", "Чеслава", "Чулпан", "Шакира", "Шарлотта", "Шахина",
            "Шейла", "Шелли", "Шерил", "Эвелина", "Эвита", "Элеонора", "Элиана", "Элиза", "Элина",
            "Элла", "Эльвина", "Эльвира", "Эльмира", "Эльнара", "Эля", "Эмили", "Эмилия", "Эмма", "Энже",
            "Эрика", "Эрмина", "Эсмеральда", "Эсмира", "Этель", "Этери", "Юлианна", "Юлия", "Юна",
            "Юния", "Юнона", "Ядвига", "Яна", "Янина", "Ярина", "Ярослава", "Ясмина"};
    // задержка. сколько милисекунд тред будет спать
    @Getter(AccessLevel.NONE)
    private static long LATENCY = 100;
    private String firstName;       // имя
    private String lastName;        // фамилия
    private byte age;               // возраст
    private Sex sex;                // пол
    private boolean isAlive;        // жив ли человек
    private double income;          // заработок
    // получаем логгер
    @Getter(AccessLevel.NONE)       // нет необходимости создавать геттер для этого поля
    private Logger log = Logger.getInstance();

    public Person(String firstName, String lastName, int age, Sex sex, double income) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age <= 100 ? (byte) age : 0;
        this.sex = sex;
        this.income = this.age < 18 ? 0 : income;
        this.isAlive = true;
        new Thread(this).start();               // начинаем жить
        log.write("Человек создан: " + this);
    }

    // сеттер для задержки
    static void setLATENCY(long latency) {
        LATENCY = latency;
    }

    /**
     * Фабричный метод для создания случайного человека, учитывая параметры
     *
     * @param surname  фамилия человека
     * @param isAdult  создавать ли взрослого. true - будет создан взрослый, false - ребенок
     * @param isFemale создавать ли женщину/мужчину. true - будет создана женщина, false - мужчина, null - без разницы
     * @return созданый объект человека
     */
    public static Person generatePerson(String surname, boolean isAdult, Boolean isFemale) {
        Random rnd = new Random();

        // проверяем параметр isFemale и исходя из его значения выбираем пол
        Sex sex = isFemale == null ?
                (rnd.nextBoolean() ? Sex.FEMALE : Sex.MALE)
                : isFemale ? Sex.FEMALE : Sex.MALE;
        // выбираем имя для человека в зависимости от его пола (случайным образом из списка)
        String name;
        if (sex == Sex.FEMALE) {
            name = FEMALE_NAMES[(int) (Math.random() * FEMALE_NAMES.length)];
        } else {
            name = MALE_NAMES[(int) (Math.random() * MALE_NAMES.length)];
        }

        // определяем фамилию человека с правильным суффиксом в зависимости от пола
        String rightSurname = sex == Sex.FEMALE ? surname.endsWith("ий")
                ? surname.substring(0, surname.lastIndexOf("ий")) + "ая"
                : surname + "а" : surname;

        // определяем возраст для нового человека в зависимости от переданого параметра isAdult
        byte age = (byte) ((isAdult ? 18 : 0) + Math.random() * (isAdult ? 42 : 18));

        // определяем доход человека случайным образом
        double income = Math.random() * 1000;

        // возвращаем оъект человека со всеми указанными параметрами
        return new Person(name, rightSurname, age, sex, income);
    }

    /**
     * Устанавливает фамилию с правильным суффиксом
     *
     * @param lastName фамилия
     */
    public void setLastName(String lastName) {
        this.lastName = sex == Sex.FEMALE ? lastName.endsWith("ий")
                ? lastName.substring(0, lastName.lastIndexOf("ий")) + "ая"
                : lastName + "а" : lastName;
    }

    @Override
    public void run() {
        // пока человек жив и пока этот тред не прерван
        while (isAlive && !Thread.currentThread().isInterrupted()) {
            // спим
            try {
                Thread.sleep(LATENCY);
            } catch (InterruptedException e) {
                // если словили этот иксепшен, значит тред пытались остановить. поэтому устанавливаем нужный флаг
                Thread.currentThread().interrupt();
            }
            // прошел год
            age++;

            // если человек взрослый - то корректируем его доход случайным образом (может быть уменьшен или увеличен)
            if (age >= 18) income += -0.5 + Math.random() * 2;

            // если постарев человек умер - выходим из цикла
            if (isDied()) break;
        }

        isAlive = false;        // ставим флаг, что этот человек больше не жилец
        log.write(this + " умер" + (sex==Sex.MALE ? "." : "ла."));

    }

    /**
     * Проверяет не пора ли человеку умереть
     * @return true - если человеку суждено умереть, false - пусть еще поживет
     */
    private boolean isDied() {
        int minDieAge = 60;         // минимальный возраст смерти
        int maxDieAge = 100;        // максимальный возраст жизни

        // если возраст текущего человека перевалил за случайны возраст жизни и смерти - возвращаем true
        return this.age >= minDieAge + Math.random() * (maxDieAge - minDieAge);
    }

    /**
     * Определяем строковое представление объекта человека
     * @return строковое представление человека
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + sex + ", возраст: " + age + ")";
    }

    /**
     * Правило сравнения людей по умолчанию
     * @param o другой человек
     * @return возращает число насколько этот человек должен стоять выше чем переданный
     */
    @Override
    public int compareTo(Person o) {
        return this.isAlive() && o.isAlive()
                ? this.age - o.age
                : this.isAlive() ? Integer.MAX_VALUE : Integer.MIN_VALUE;
    }

    /**
     * Пол человека (женский или мужской)
     */
    public enum Sex {
        FEMALE,
        MALE
    }
}

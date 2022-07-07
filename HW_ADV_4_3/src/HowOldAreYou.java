import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Домашнее задание 4
 * Задание 3
 * Спроектируйте и разработайте метод, определяющий, сколько времени прошло с заданной даты.
 * С помощью этого методы выведите в консоль, сколько времени прошло с вашего дня рождения в удобном для восприятия виде,
 * например: «Вам исполнилось 20 лет, 3 месяца, 18 дней, 4 часа, 5 минут и 10 секунд».
 */

public class HowOldAreYou {
    public static void main(String[] args) {
        int myYear = 1980;
        int myMonth = 5;
        int myDayOfMonth = 4;

        LocalDate myDate = LocalDate.of(myYear, myMonth, myDayOfMonth);
        LocalDate today = LocalDate.now();
        int myDateYear = myDate.getMonthValue() > today.getMonthValue() ? today.getYear() - 1 - myDate.getYear() : today.getYear() - myDate.getYear();
        int myDateMonth = myDate.getMonthValue() > today.getMonthValue() ? 12 - myDate.getMonthValue() + today.getMonthValue() : today.getMonthValue() - myDate.getMonthValue();
        int myDateDay = myDate.getDayOfMonth() > today.getDayOfMonth() ? 30 - myDate.getDayOfMonth() + today.getDayOfMonth() : today.getDayOfMonth() - myDate.getDayOfMonth();
        System.out.println("Твой день рождения: " + myDate.getDayOfMonth() + " - " + myDate.getMonth() + " - " + myDate.getYear());
        System.out.println("1. С Вашего дня рождения прошло: " + myDateYear + " year, " + myDateMonth + " month, " + myDateDay + " day");
        System.out.println("2. С Вашего дня рождения прошло: " + ChronoUnit.YEARS.between(myDate, today) + " year, " + ChronoUnit.MONTHS.between(myDate, today) + " month, " + ChronoUnit.DAYS.between(myDate, today) + " day");

        System.out.println();
        LocalTime time = LocalTime.of(8, 40, 15);
        LocalDateTime myTime = myDate.atTime(time);
        LocalDateTime nowTime = LocalDateTime.now();
        System.out.println("3. myTime  = " + myTime);
        System.out.println("4. nowTime = " + nowTime);

        System.out.println("5.  С Вашего дня рождения прошло: " + ChronoUnit.YEARS.between(myTime, nowTime) + " YEARS??? " + ChronoUnit.MONTHS.between(myTime, nowTime) % ChronoUnit.YEARS.between(myTime, nowTime) + " MONTHS??? ");
        System.out.println("Получаем текущее время от класса LocalDateTime - метод LocalDateTime.now()");
        System.out.println("6.  Year       = " + nowTime.getYear());
        System.out.println("7.  Month      = " + nowTime.getMonthValue() + " , or = " + nowTime.getMonth());
        System.out.println("8.  DayOfMonth = " + nowTime.getDayOfMonth());
        System.out.println("9.  Hour       = " + nowTime.getHour());
        System.out.println("10. Minute     = " + nowTime.getMinute());
        System.out.println("11. Second     = " + nowTime.getSecond());


    }
}

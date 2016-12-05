package northpole.nick.elf;

import java.util.Calendar;
import java.util.GregorianCalendar;

public final class ListChecker {

    private final Calendar calendar = GregorianCalendar.getInstance();

    public ListChecker() {

    }

    /**
     * Check whether someone is on the naughty list or the nice list. Returns <code>true</code> if
     * the provided name is on the nice list or <code>false</code> if on the naughty list.
     *
     * @param name of the person to check
     * @return <code>true</code> if they're on the nice list or <code>false</code> otherwise
     */
    public boolean naughtyOrNice(String name) {
        return (calendar.get(Calendar.DAY_OF_MONTH) + name.hashCode()) % 2 == 0;
    }
}

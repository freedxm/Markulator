package processor;


import handler.ConsoleHandler;
import util.Messages;

import java.util.HashMap;
import java.util.Map;


public class AppProcessor {
    private ConsoleHandler handler;
    private final Map<Integer, String> subjects = new HashMap();

    public void run() {
        init();
        initSubjectsMap(subjects);
        toGreet();
        writeSubjects();
        getSubject();
        getAllTheMarksAndReturnAverage();
    }

    public void toGreet() {
        handler.writeLine();
        handler.write(Messages.GREETING);
        handler.write(Messages.DESCRIPTION);
        handler.writeLine();
    }

    public void init() {
        this.handler = new ConsoleHandler();
    }

    public void initSubjectsMap(Map<Integer, String> subjects) {
        subjects.put(1, "1. Англ. яз.");
        subjects.put(2, "2. Бел. лiт.");
        subjects.put(3, "3. Бел. мова");
        subjects.put(4, "4. Биология O_o");
        subjects.put(5, "5. Всемир. ист.");
        subjects.put(6, "6. География");
        subjects.put(7, "7. Информ.");
        subjects.put(8, "8. Искусство");
        subjects.put(9, "9. Ист. Бел.");
        subjects.put(10, "10. Матем.");
        subjects.put(11, "11. Рус. лит.");
        subjects.put(12, "12. Рус. яз.");
        subjects.put(13, "13. Труд. обуч.");
        subjects.put(14, "14. Физика");
        subjects.put(15, "15. Физ. к. и зд.");
        subjects.put(16, "16. Химия");
    }

    public void writeSubjects() {
        for (Map.Entry<Integer, String> pair : subjects.entrySet()) {
            handler.write(pair.getValue());
        }
    }

    public void getSubject() {
        int subjectInteger;
        while (true) {
            String subjectString = handler.read();
            if (isSubject(subjectString)) {
                subjectInteger = Integer.parseInt(subjectString);
                break;
            } else {
                handler.write(Messages.SUBJECT_IS_WRONG);
            }
        }
        for (Map.Entry<Integer, String> pair : subjects.entrySet()) {
            if (pair.getKey() == subjectInteger) {
                handler.write(Messages.CHOSEN_SUBJECT + pair.getValue() + Messages.WRITE_MARKS);
            }
        }
    }

    public String getOneMark() {
        while (true) {
            String markString = handler.read();
            if (isMark(markString) || markString.equalsIgnoreCase("Ok")) {
                return markString;
            } else {
                handler.write(Messages.MARK_IS_WRONG);
            }
        }
    }

    public boolean isMark(String str) {
        String regex = "([1-9]|1[0])";
        if (str.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSubject(String str) {
        String regex = "([1-9]|1[0-6])";
        if (str.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    public void getAllTheMarksAndReturnAverage() {
        int marksCounter = 0;
        int sumOfMarks = 0;
        while (true) {
            String mark = getOneMark();
            if (mark.equalsIgnoreCase("Ok")) {
                break;
            } else {
                sumOfMarks += Integer.parseInt(mark);
                marksCounter++;
            }
        }
        handler.write(Messages.AVERAGE + getAverage(sumOfMarks, marksCounter));
    }

    public double getAverage(int sumOfMarks, int marksCounter) {
        return (double) sumOfMarks / (double) marksCounter;
    }
}

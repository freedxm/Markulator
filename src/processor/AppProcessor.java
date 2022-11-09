package processor;

import handler.ConsoleHandler;
import model.Subject;
import util.Messages;

import java.util.ArrayList;
import java.util.List;

public class AppProcessor {
    private ConsoleHandler handler;
    private final List<Subject> subjectsList = new ArrayList<>();

    public void run() {
        init();
        initSubjectsList(subjectsList);
        toGreet();
        while (true) {
            writeSubjectNamesAndMarks();
            int currentIndex = getSubjectAndReturnIndex();
            double currentAverage = getAllTheMarksAndReturnAverage();
            if (currentAverage != -1) {
                subjectsList.set(currentIndex - 1, new Subject(subjectsList.get(currentIndex - 1).getName(), currentAverage));
            }
            goBack();
        }
    }

    private void init() {
        this.handler = new ConsoleHandler();
    }

    private void initSubjectsList(List<Subject> subjects) {
        subjects.add(new Subject("1. Англ. яз."));
        subjects.add(new Subject("2. Бел. лiт."));
        subjects.add(new Subject("3. Бел. мов"));
        subjects.add(new Subject("4. Биология O_o"));
        subjects.add(new Subject("5. Всемир. ист."));
        subjects.add(new Subject("6. География"));
        subjects.add(new Subject("7. Информ."));
        subjects.add(new Subject("8. Искусство"));
        subjects.add(new Subject("9. Ист. Бел."));
        subjects.add(new Subject("10. Матем."));
        subjects.add(new Subject("11. Рус. лит."));
        subjects.add(new Subject("12. Рус. яз."));
        subjects.add(new Subject("13. Труд. обуч."));
        subjects.add(new Subject("14. Физика"));
        subjects.add(new Subject("15. Физ. к. и зд."));
        subjects.add(new Subject("16. Химия"));
    }

    private void toGreet() {
        handler.writeLine();
        handler.write(Messages.GREETING);
        handler.write(Messages.DESCRIPTION);
        handler.write(Messages.ENTER_TOTAL_AVERAGE);
        handler.write(Messages.ENTER_STOP);
        handler.writeLine();
    }

    private void writeSubjectNamesAndMarks() {
        for (Subject subject : subjectsList) {
            handler.write(subject.getAverage() == 0.0 ? subject.getName() : subject.getName() + ": " + subject.getAverage());
        }
    }

    private int getSubjectAndReturnIndex() {
        int subjectIndex;
        while (true) {
            String subjectString = handler.read();
            if (isSubject(subjectString)) {
                subjectIndex = Integer.parseInt(subjectString);
                break;
            } else if (subjectString.equalsIgnoreCase(Messages.AV)) {
                if (printAverageOfAllTheSubjects() == -1) {
                    handler.write(Messages.AV_IS_NuN);
                }

            } else if (subjectString.equalsIgnoreCase(Messages.STOP_STRING)) {
                handler.write(Messages.STOP);
                System.exit(0);
            } else {
                handler.write(Messages.SUBJECT_IS_WRONG);
            }
        }
        writeChosenSubject(subjectIndex);
        return subjectIndex;
    }

    private void writeChosenSubject(int subjectInteger) {
        for (Subject subject : subjectsList) {
            if (subjectsList.indexOf(subject) == subjectInteger - 1) {
                handler.write(Messages.CHOSEN_SUBJECT + subject.getName() + Messages.WRITE_MARKS);
            }
        }
    }

    private String getOneMark() {
        while (true) {
            String markString = handler.read();
            if (isMark(markString) || markString.equalsIgnoreCase("Ok")) {
                return markString;
            } else {
                handler.write(Messages.MARK_IS_WRONG);
            }
        }
    }

    private double getAllTheMarksAndReturnAverage() {
        handler.write(Messages.ENTER_OK);
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
        double average = getAverage(sumOfMarks, marksCounter);
        if (marksCounter == 0) {
            handler.write(Messages.NULL_MARKS);
            average = -1;
        } else {
            handler.write(Messages.AVERAGE + average);
        }
        return average;
    }

    private double getAverage(int sumOfMarks, int marksCounter) {
        return (double) sumOfMarks / (double) marksCounter;
    }

    private void goBack() {
        handler.write(Messages.ENTER_BACK);
        while (true) {
            String str = handler.read();
            if (str.equalsIgnoreCase(Messages.BACK_STRING)) {
                break;
            } else {
                handler.write(Messages.NOT_STOP_AND_BACK);
            }
        }
    }

    private boolean isMark(String str) {
        String regex = "([1-9]|1[0])";
        return str.matches(regex);
    }

    private boolean isSubject(String str) {
        String regex = "([1-9]|1[0-6])";
        return str.matches(regex);
    }

    private double printAverageOfAllTheSubjects() {
        int totalCounterOfMarks = 0;
        double totalSum = 0;
        for (Subject subject : subjectsList) {
            if (subject.getAverage() != 0) {
                totalSum += subject.getAverage();
                totalCounterOfMarks++;
            }
        }
        double totalAverage = totalSum / totalCounterOfMarks;
        if (totalCounterOfMarks == 0) {
            return -1;
        }
        handler.write(Messages.TOTAL_AVERAGE + totalAverage);
        return totalAverage;
    }
}

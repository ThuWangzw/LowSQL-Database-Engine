select * from teacher where TEACH_ID > 0;
select teacher.name, student.STUDY_ID from teacher join student on teacher.TEACH_ID = student.STUDY_ID;
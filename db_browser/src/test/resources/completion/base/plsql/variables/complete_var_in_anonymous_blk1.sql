DECLARE
    lsdf    NUMBER;
BEGIN
    -- Display the first 5 names to show they're messed up
    FOR person IN (
            SELECT first_name,
                last_name
            FROM
                employee_temp
            WHERE ROWNUM < 6
            )
    LOOP
        dbms_output.put_line(person.first_name || ' ' ||
            person.last_name);
    END LOOP;
    -- Display the first 5 names to show they've been fixed up
    FOR person IN (SELECT *
                   FROM employee_temp
                   WHERE ROWNUM < 6)
    LOOP
        dbms_output.put_line(person.first_name || ' ' || person.last_name);
    END LOOP;
    <caret>
END;
/

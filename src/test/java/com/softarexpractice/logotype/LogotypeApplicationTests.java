package com.softarexpractice.logotype;

import com.softarexpractice.logotype.model.Field;
import com.softarexpractice.logotype.model.FieldType;
import com.softarexpractice.logotype.model.User;
import com.softarexpractice.logotype.service.FieldService;
import com.softarexpractice.logotype.service.UserService;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log
@SpringBootTest
class LogotypeApplicationTests {

    @Autowired
    private FieldService fieldService;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    @Before(value = "addField")
    void addUser() {
        User user = new User();
        user.setEmail("email@doma.in");
        user.setPassword("pass");
        user.setFirstName("al");
        user.setLastName("kom");
        user.setPhone("+(10)3489794");
        userService.saveUser(user);
    }

    @After("addUser")
    @Before(value = "deleteField")
    @Test
    void addField() {
        Field field = new Field();
        field.setLabel("Hobby");
        field.setType(FieldType.SINGLE_LINE);
        field.setActive(true);
        field.setRequired(false);
        field.setUser(userService.findAll().get(0));
        field.setOptions(null);
        fieldService.saveField(field);
    }

    @Disabled
    @Test
    void updateField() {
        Field field = null;
        Optional<Field> optional = fieldService.findAll()
                .stream().filter(f -> f.getLabel().equals("BDay"))
                .findFirst();
        if (optional.isPresent()) {
            field = optional.get();
            field.setType(FieldType.DATE);
            fieldService.saveField(field);
        }
        assert (field != null && field.getType().equals(FieldType.DATE));
    }

    @Transactional
    @Test
    @After("addField")
    void deleteField() {
        int oldSize = fieldService.findAll().size();
        fieldService.deleteById(fieldService.findAll().get(1).getId());
        assert (oldSize - 1 == fieldService.findAll().size());
    }

    @Test
    @After("addUser")
    void deleteUser() {
        int oldSize = userService.findAll().size();
        userService.deleteById(userService.findAll().get(1).getId());
        assert (userService.findAll().size() + 1 == oldSize);
    }

    @Disabled
    @Transactional
    @Test
    void grabUserFields() {
        // User user1 = new User();
        // user1.setEmail("fir@mail.mm");
        // user1.setPassword("pass");
        // user1.setFirstName("user1");
        //
        // Field field1 =  new Field();
        // field1.setLabel("one");
        // field1.setType(FieldType.SINGLE_LINE);
        // field1.setUser(user1);
        //
        // Field field2 =  new Field();
        // field2.setLabel("two");
        // field2.setType(FieldType.CHECKBOX);
        // field2.setOptions(Arrays.asList("1", "2", "3"));
        // field2.setUser(user1);
        //
        // User user2 = new User();
        // user2.setEmail("sec@mail.mm");
        // user2.setPassword("pass");
        // user2.setFirstName("user2");
        //
        // Field field3 =  new Field();
        // field3.setLabel("three");
        // field3.setType(FieldType.DATE);
        // field3.setUser(user2);
        //
        // user1 = userService.saveUser(user1);
        // user2 = userService.saveUser(user2);
        // field1 = fieldService.saveField(field1);
        // field2 = fieldService.saveField(field2);
        // field3 = fieldService.saveField(field3);

        // List<Field> allByUser1 = fieldService.findByUser(user1);
        // List<Field> allByUser2 = fieldService.findByUser(user2);

        User user1 = userService.findById((long) 5);
        User user2 = userService.findById((long) 6);
        List<Field> byUser1 = fieldService.findByUser(user1);
        List<Field> byUser2 = fieldService.findByUser(user2);
        // log.info("allByUser1: " + byUser1);
        // log.info("allByUser2: " + byUser2);

        byUser1.retainAll(byUser2);

        assert(byUser1.isEmpty());

        // fieldService.deleteById((long) 7);
        // fieldService.deleteById((long) 8);
        // fieldService.deleteById((long) 9);
        // userService.deleteById((long) 5);
        // userService.deleteById((long) 6);
    }
}

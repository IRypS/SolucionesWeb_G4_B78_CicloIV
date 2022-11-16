package com.soluciones.web.appGrupo4.repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.UserList;

// -------------> Temporally class <-------------

@Repository
public class ListRepository implements IListRepository {
    
    @Override
    public List<UserList> getAllUserListObjects() {

        UserList list1 = new UserList();
        UserList list2 = new UserList();
        UserList list3 = new UserList();
        UserList list4 = new UserList();
        UserList list5 = new UserList();

        list1.setId(UUID.randomUUID().toString());
        list1.setName("Lista creada 1");

        list2.setId(UUID.randomUUID().toString());
        list2.setName("Lista creada 2");

        list3.setId(UUID.randomUUID().toString());
        list3.setName("Lista creada 3");

        list4.setId(UUID.randomUUID().toString());
        list4.setName("Lista creada 4");

        list5.setId(UUID.randomUUID().toString());
        list5.setName("Lista creada 5");

        List<UserList> lists = Arrays.asList(list1, list2, list3, list4, list5);

        return lists;
    };
};

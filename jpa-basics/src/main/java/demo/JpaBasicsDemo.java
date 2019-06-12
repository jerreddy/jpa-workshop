package demo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaBasicsDemo {

    public static void main(String[] args) {
        //insertContact("prasad","prasad@gmail.com");
        //updateContact(2L, "prasad123","prasad123@gmail.com");
        //insertUser("prasad","prasad@gmail.com");
       // insertUserWithRoles("prasad","prasad@gmail.com");
        getContactById(1L);
        updateContactById(1L,"prasad","prasad@gmail.com");
        getAllContacts();
        deleteContactById(2L);
        getAllContacts();

        getUserById(1L);
    }

    //Lazy initialization
    static void getUserById(Long id) {
        EntityManager entityManager = EMF.getEntityManager();
        User user = entityManager.find(User.class, id);
        System.out.println(user);
        //entityManager.close();
        List<Message> messages = user.getMessages();
        System.out.println(messages);
    }

    static void getAllContacts() {
        EntityManager entityManager = EMF.getEntityManager();
        TypedQuery<Contact> query = entityManager.createQuery("select c from Contact c", Contact.class);
        List<Contact> contacts = query.getResultList();
        System.out.println(contacts);
    }

    static void getContactById(Long id) {
        EntityManager entityManager = EMF.getEntityManager();
        Contact contact = entityManager.find(Contact.class, id);
        System.out.println(contact);
    }

    static void updateContactById(Long id, String name, String email) {
        EntityManager entityManager = EMF.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Contact contact = entityManager.find(Contact.class, id);
        contact.setName(name);
        contact.setEmailId(email);
        transaction.commit();
        entityManager.close();
    }


    static void deleteContactById(Long id) {
        EntityManager entityManager = EMF.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Contact contact = entityManager.find(Contact.class, id);
        if(contact != null) {
            entityManager.remove(contact);
        }
        transaction.commit();
        entityManager.close();
    }

    // OneToOne
    static void insertUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmailId(email);
        Address address = new Address();
        address.setCity("Hyderabad");
        user.setAddress(address);

        EntityManager entityManager = EMF.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
    }

    // ManyToMany
    static void insertUserWithRoles(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmailId(email);
        Address address = new Address();
        address.setCity("Hyderabad");
        user.setAddress(address);

        Role role1 = new Role();
        role1.setName("ROLE_ADMIN");
        role1.getUsers().add(user);

        Role role2 = new Role();
        role2.setName("ROLE_USER");
        role2.getUsers().add(user);

        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);
        user.setRoles(roles);

        EntityManager entityManager = EMF.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
    }

    static void insertContact(String name, String email) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmailId(email);
        EntityManager entityManager = EMF.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(contact);
        transaction.commit();
        entityManager.close();
    }

    static void updateContact(Long id, String name, String email) {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setEmailId(email);
        EntityManager entityManager = EMF.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(contact);
        transaction.commit();
        entityManager.close();
    }

}


class EMF {
    private static EntityManagerFactory INSTANCE = null;

    private EMF() {
    }

    public static EntityManagerFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = Persistence.createEntityManagerFactory("defaultPU");
        }
        return INSTANCE;
    }

    public static EntityManager getEntityManager() {
        return getInstance().createEntityManager();
    }
}
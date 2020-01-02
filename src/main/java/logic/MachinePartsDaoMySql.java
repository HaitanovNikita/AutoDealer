package logic;

import org.hibernate.Session;
import tables.ModelCar;

import java.util.ArrayList;



public class MachinePartsDaoMySql<T> implements MachinePartsDao<T> {

    public Class clazz;
    private Session session;

    public MachinePartsDaoMySql(Class clazz){
        this.clazz = clazz;
        session =  HibernateUtils.getSessionFactory().getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public  void create(T object) {
       try{
        session.getTransaction().begin();
        session.persist(object);
        session.getTransaction().commit();}
       catch(Exception e){
           e.printStackTrace();
           session.getTransaction().rollback();
       }
    }

    @Override
    @SuppressWarnings("unchecked")
    public  void update(T object) {
        try{
            session.getTransaction().begin();
            session.update(object);
            session.getTransaction().commit();}
        catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public  void delete(T object) {
        try{
            session.getTransaction().begin();
            session.delete(object);
            session.getTransaction().commit();}
        catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public  T find(int id) {
        T t = null;
        try {
            session.getTransaction().begin();
            String[] arrTable = clazz.toString().split("tables.");
            ArrayList<T> tArrayList = (ArrayList<T>) session.createQuery("Select c from "+arrTable[1].trim()+" as c where c.ID = "+ id).getResultList();
            if(tArrayList.isEmpty()!=true) {
                t = tArrayList.get(0);
                tArrayList.stream().forEach((c)-> System.out.println(c.toString()));
            }
            session.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return t;
    }


    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<T> read() {
        ArrayList<T> tArrayList = null;
        try {
            session.getTransaction().begin();
            String[] arrTable = clazz.toString().split("tables.");
            tArrayList = (ArrayList<T>) session.createQuery("Select c from "+arrTable[1].trim()+" as c").getResultList();
            session.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return tArrayList;
    }
}

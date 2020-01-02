package logic;

import java.util.ArrayList;

public interface MachinePartsDao<T> {

      void create(T object);
      void update(T object);
      void delete(T object);
      T find(int id);
      ArrayList<T> read();

}

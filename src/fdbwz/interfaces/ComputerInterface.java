package fdbwz.interfaces;

import fdbwz.Model.Computer;

import java.util.ArrayList;


public interface ComputerInterface {

        ArrayList<Computer> getAll();
        Computer getById(int ComputerId);
        void insert(Computer computer);
        void update(Computer computer);
        void delete(int ComputerId);
        void save();
}

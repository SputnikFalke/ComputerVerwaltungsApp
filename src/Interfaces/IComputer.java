package Interfaces;

import Model.Computer;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public interface IComputer {
    Computer DocToComputer(Document doc);
    ArrayList<Computer> getAll(Document doc, ArrayList<Computer> computers);
    Computer getById(int index, MongoCursor<Document> cursor);
    void update(Computer computer);
    ObjectId delete(MongoCursor<Document> cursor);
}

package Interfaces;

import Model.Computer;
import Model.Kunde;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public interface IKunde {

    Kunde DocToKunde(Document doc);
    ArrayList<Kunde> getAll(Document doc, ArrayList<Kunde> computers);
    Kunde getById(int index, MongoCursor<Document> cursor);
    void update(Kunde kunde);
    ObjectId delete(MongoCursor<Document> cursor);

}

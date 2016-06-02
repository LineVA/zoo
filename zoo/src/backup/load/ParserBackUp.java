package backup.load;

import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import zoo.IZoo;
import zoo.Zoo;
import zoo.paddock.IPaddock;
import zoo.paddock.Paddock;
import zoo.paddock.PaddockCoordinates;

/**
 *
 * @author doyenm
 */
public class ParserBackUp {

    Element zooEl;

    public ParserBackUp(File file) throws IOException, JDOMException {
        SAXBuilder sax = new SAXBuilder();
        Document document;
        document = sax.build(file);
        this.zooEl = document.getRootElement();
    }

    public IZoo parserZoo() throws EmptyNameException, IOException {
        IZoo zoo = new Zoo();
        Element dimEl = zooEl.getChild("dimensions");
        zoo.initiateZoo(zooEl.getAttributeValue("name"),
                Integer.parseInt(dimEl.getChild("width").getText()),
                Integer.parseInt(dimEl.getChild("height").getText()), null);
        return zoo;
    }

    public ArrayList<IPaddock> parserPaddock() throws IncorrectDimensionsException {
        Element paddocksEl = zooEl.getChild("paddocks");
        List<Element> paddocksElList = paddocksEl.getChildren("paddock");
        ArrayList<IPaddock> paddocksList = new ArrayList<>();
        Iterator it = paddocksElList.iterator();
        Element tmpPadEl;
        PaddockCoordinates tmpCoor;
        while (it.hasNext()) {
            tmpPadEl = (Element) it.next();
            tmpCoor = new PaddockCoordinates(Integer.parseInt(tmpPadEl.getChildText("x")),
                    Integer.parseInt(tmpPadEl.getChildText("y")),
                    Integer.parseInt(tmpPadEl.getChildText("width")),
                    Integer.parseInt(tmpPadEl.getChildText("height")));
            paddocksList.add(new Paddock(tmpPadEl.getAttributeValue("name"), tmpCoor));
        }
        return paddocksList;
    }
}

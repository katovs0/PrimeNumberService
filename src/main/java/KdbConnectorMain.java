import com.kdb.dao.KdbDaoFactory;
import com.kdb.dao.KdbDaoPool;
import com.kdb.dao.KdbTable;
import com.kdb.dao.utils.FlipParser;
import kx.c;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.IOException;
import java.util.List;

public class KdbConnectorMain {

    public static void main(String[] args) throws Exception {


        testDaoPool();

    }

    private static void testDaoPool() throws Exception {
        KdbDaoFactory factory = new KdbDaoFactory("localhost", 5555);
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(5);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        KdbDaoPool pool = new KdbDaoPool(factory, config);

        Object result = pool.borrowObject().executeQuery("tab");

        List<KdbTable> dataList = FlipParser.parseFlip((c.Flip) result, KdbTable.class);

        System.out.println(dataList);
    }

    private void simpleConnectionTest()  throws IOException, c.KException  {
        c connection = new c("localhost", 5555);

        Object result = connection.k("tab");

        List<KdbTable> dataList = FlipParser.parseFlip((c.Flip) result, KdbTable.class);

        System.out.println(dataList);
    }
}

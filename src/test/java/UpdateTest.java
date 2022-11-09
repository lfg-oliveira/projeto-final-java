
import com.company.estoque.model.Constraint;
import com.company.estoque.model.Products;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guilu
 */
public class UpdateTest {
    @Test
    public void updateTest(){
        Products p = new Products();
        p.addColumn("name", "varchar(50)", new Constraint());
        p.createTable();
        var updates = new HashMap<String, Object>();
        
        updates.put("name", "'Luiz'");
        var res = p.update(updates);
        Assert.assertEquals("UPDATE Products SET name = "
                + "'Luiz';", res);
    }
}

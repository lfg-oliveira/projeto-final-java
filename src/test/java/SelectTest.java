
import com.company.estoque.model.Products;
import java.sql.SQLException;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guilu
 */
public class SelectTest {
    @Test
    public void selectTest() {
        try{
        Products p = new Products();
        var res = p.select();
        while(res.next())
        {
          System.out.println(res.getString(1));  //First Column
        }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
}

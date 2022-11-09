
import com.company.estoque.model.Constraint;
import com.company.estoque.model.Database;
import com.company.estoque.model.Products;
import java.sql.SQLException;
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
public class DatabaseTest {
    @Test
    public void testNewDatabase() {
        boolean res = false;
        try {
            res = Database.getConnection().createStatement().execute(
                "CREATE TABLE IF NOT EXISTS test("
        + "nome varchar(50),"
        + "salario real check(salario > 0)"
        + ");");
        }
        catch (SQLException e) {
            System.err.println(e);
        }
        Assert.assertFalse(res);
    }
}

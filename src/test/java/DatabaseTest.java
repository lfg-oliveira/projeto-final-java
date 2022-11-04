
import com.company.estoque.model.Constraint;
import com.company.estoque.model.Database;
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
public class DatabaseTest {
    @Test
    public void testNewDatabase() {
        try {
            Database.getConnection().createStatement().execute(
                "CREATE TABLE test("
        + "nome varchar(50),"
        + "salario real check(salario > 0)"
        + ");");
        }
        catch (SQLException e) {
            System.err.println(e);
        }
    }
}

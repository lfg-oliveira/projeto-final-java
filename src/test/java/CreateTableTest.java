
import com.company.estoque.model.Constraint;
import com.company.estoque.model.Products;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guilu
 */
public class CreateTableTest {
    @Test
    public void createTable() {
        Products prod = new Products();
        prod.addColumn("name", "varchar(50)", new Constraint(Constraint.NOT_NULL));
        prod.createTable();
    }
    
}

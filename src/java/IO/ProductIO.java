/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import business.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProductIO {

    public static Product getProduct(String productCode, String path) throws FileNotFoundException, IOException {
        ArrayList<Product> listProducts = new ArrayList<>();
        Product product = new Product();
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line;
        while ((line = br.readLine()) != null) {
            String[] st = line.split("\\|");
            Product p = new Product(st[0], st[1], (float) Double.parseDouble(st[2]));
            listProducts.add(p);
        }

        //Bước 3: Đóng luồng
        br.close();
        for (Product p : listProducts) {
            if (p.getCode().equals(productCode)) {
                product = p;
                break;
            }
        }
        return product;
    }
}

package vn.fis.training.ordermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.fis.training.ordermanagement.dto.ProductDTO;
import vn.fis.training.ordermanagement.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query("select p from Product p")
//    List<ProductDTO> findAllWithNameOnly();

    List<ProductDTO> findAllProjectedBy();

    List<ProductDTO> findAllByName(String productName);

}

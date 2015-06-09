package br.com.ecoder.taxis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ecoder.taxis.model.Driver;

@Repository
//@formatter:off
public interface DriversRepository extends JpaRepository<Driver, Long> {

    @Query(" SELECT driver " +
           "   FROM Driver driver " +
           "  WHERE driver.latitude  BETWEEN :lat1 AND :lat2 " +
           "    AND driver.longitude BETWEEN :lng1 AND :lng2")
    public List<Driver> findInArea(
            @Param("lat1") Double lat1, 
            @Param("lng1") Double lng1, 
            @Param("lat2") Double lat2, 
            @Param("lng2") Double lng2);

}

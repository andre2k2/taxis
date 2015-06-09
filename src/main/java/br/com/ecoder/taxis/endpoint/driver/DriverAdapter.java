package br.com.ecoder.taxis.endpoint.driver;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.ecoder.taxis.model.Driver;

public class DriverAdapter {

    public static final DriverCreateResponse adaptEntityToCreateResponse(Driver entity) {

        DriverCreateResponse response = new DriverCreateResponse();
        response.setDriverId(entity.getId());

        return response;
    }

    public static final List<DriverStatusResponse> adaptEntityToStatusResponse(List<Driver> entities) {

        if (entities != null) {

            LinkedList<DriverStatusResponse> response = new LinkedList<DriverStatusResponse>();

            for (Driver entity : entities) {
                response.add(adaptEntityToStatusResponse(entity));
            }

            return response;
        }

        return null;
    }

    public static final DriverStatusResponse adaptEntityToStatusResponse(Driver entity) {

        DriverStatusResponse response = new DriverStatusResponse();

        BeanUtils.copyProperties(entity, response);

        response.setDriverId(entity.getId());

        return response;
    }

    public static final Driver adaptCreateRequestToEntity(DriverCreateRequest request) {

        Driver entity = new Driver();

        BeanUtils.copyProperties(request, entity);

        return entity;
    }

    public static final Driver adaptStatusRequestToEntity(DriverStatusRequest request) {

        Driver entity = new Driver();

        BeanUtils.copyProperties(request, entity);

        entity.setId(request.getDriverId());

        return entity;
    }

}

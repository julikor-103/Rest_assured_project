package pojo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "tags", "status" })

public class Pet {
    public static final String PATH_REQUESTS_ID = "/pet/{petId}";
    public static final String PATH_PETS = "/pet";
    public static final String ID_PATH = "id";
    public static final String NAME_PATH = "name";
    public static final String STATUS_PATH = "status";

    public static String getPathRequestsId() {
        return PATH_REQUESTS_ID;
    }

    public static String getPathPets() {
        return PATH_PETS;
    }

    public static String getIdPath() {
        return ID_PATH;
    }

    public static String getNamePath() {
        return NAME_PATH;
    }

    public static String getStatusPath() {
        return STATUS_PATH;
    }

    public Pet() {
    }
}


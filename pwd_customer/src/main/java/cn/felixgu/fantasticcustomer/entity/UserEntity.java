package cn.felixgu.fantasticcustomer.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user", schema = "test", catalog = "")
public class UserEntity implements Serializable{
    private int id;
    private int imageId;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "image_id")
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserEntity that = (UserEntity) o;

        if (id != that.id) {
            return false;
        }
        if (imageId != that.imageId) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + imageId;
        return result;
    }
}

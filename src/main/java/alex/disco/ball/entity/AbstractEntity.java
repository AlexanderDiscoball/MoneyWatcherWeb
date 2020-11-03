package alex.disco.ball.entity;

import alex.disco.ball.util.DateProcessor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {



//    @Version
//    protected int version;

    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = DateProcessor.DATE_FORMAT)
    protected LocalDate createdAt;

    @Column(name = "modified_at", nullable = false)
    @DateTimeFormat(pattern = DateProcessor.DATE_FORMAT)
    protected LocalDate modifiedAt;

    protected AbstractEntity() {
        createdAt = LocalDateTime.now().toLocalDate();
        modifiedAt = LocalDateTime.now().toLocalDate();
    }

    public void setModifiedAt(LocalDate modifiedAt) {
        this.modifiedAt = modifiedAt;
    }


    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getModifiedAt() {
        return modifiedAt;
    }

}

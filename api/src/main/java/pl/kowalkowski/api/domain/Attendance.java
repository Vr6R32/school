package pl.kowalkowski.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "attendances")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Child child;

    @Column(nullable = false)
    private LocalDateTime entryDate;

    @Column(nullable = false)
    private LocalDateTime exitDate;

}
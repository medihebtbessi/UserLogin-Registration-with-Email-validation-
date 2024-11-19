package find_your_house.notification;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Notification {
    private NotificationStatus status;
    private String massage;
    private String reservationName;
}

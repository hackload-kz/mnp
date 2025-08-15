package kz.mn.partners.mnp.v1.biletter.dal.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventEntity {
    private Long id;
    private String title;
    private Boolean external;
}



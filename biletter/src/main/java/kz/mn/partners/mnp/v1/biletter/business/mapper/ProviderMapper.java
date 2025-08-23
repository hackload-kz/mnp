package kz.mn.partners.mnp.v1.biletter.business.mapper;

import kz.mn.partners.mnp.v1.biletter.client.response.SeatResponse;
import kz.mn.partners.mnp.v1.biletter.dal.entity.SeatEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProviderMapper {


    public SeatEntity toListSeatEntity(SeatResponse seatResponse) {
        return SeatEntity.builder()
            .seatRow(seatResponse.getSeat())
            .number(seatResponse.getRow())
            .build();
    }
}

package kz.mn.partners.mnp.v1.biletter.business.service;

import kz.mn.partners.mnp.v1.biletter.business.dto.request.CancelBookingRequest;
import kz.mn.partners.mnp.v1.biletter.business.dto.request.InitiatePaymentRequest;
import kz.mn.partners.mnp.v1.biletter.dal.entity.BookingEntity;
import kz.mn.partners.mnp.v1.biletter.dal.entity.UserEntity;
import kz.mn.partners.mnp.v1.biletter.dal.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Optional<BookingEntity> getByOrderIdWithLock(String orderId) {
        return bookingRepository.findByOrderIdWithLock(orderId);
    }

    public BookingEntity getByOrderId(String orderId) {
        return bookingRepository.findByOrderId(orderId);
    }

    public BookingEntity getById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No booking found with id: " + id));
    }

    public List<BookingEntity> getByUser(UserEntity user) {
        return bookingRepository.findByUser(user);
    }

    public BookingEntity save(BookingEntity entity) {
        return bookingRepository.save(entity);
    }

    public BookingEntity create(BookingEntity entity) {
        return null;
    }

    public void initiatePayment(InitiatePaymentRequest request) {

    }

    public void cancel(CancelBookingRequest request) {

    }

    public List<BookingEntity> getBookings() {
        return List.of();
    }
}

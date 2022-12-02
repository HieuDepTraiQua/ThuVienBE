package com.quanly.thuvien.service;

import com.quanly.thuvien.dto.ReviewDTO;
import com.quanly.thuvien.model.AccountModel;
import com.quanly.thuvien.model.ReviewModel;
import com.quanly.thuvien.repository.AccountRepository;
import com.quanly.thuvien.repository.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<ReviewModel> getAll() {
        return reviewRepository.findAll();
    };

    public ReviewModel create(ReviewModel review) {
        return reviewRepository.save(review);
    };

    public ReviewModel update(ReviewModel review, String id) {
        Optional<ReviewModel> optional = reviewRepository.findById(id);
        if (!optional.isPresent()) {
            throw new EntityNotFoundException("Id not found!");
        } else {
            review.setId(id);
            if (review.getVote() == null || review.getVote().isEmpty()) {
                review.setVote(optional.get().getVote());
            }
            if (review.getDetail() == null || review.getDetail().isEmpty()) {
                review.setDetail(optional.get().getDetail());
            }
            if (review.getUserId() == null || review.getUserId().isEmpty()) {
                review.setUserId(optional.get().getUserId());
            }
            return reviewRepository.save(review);
        }
    };

    public void deleteById(String id) {
        Optional<ReviewModel> optional = reviewRepository.findById(id);
        if (!optional.isPresent()) {
            throw new EntityNotFoundException("Id not found!");
        } else {
            reviewRepository.deleteById(id);
        }
    };
    public Page<ReviewDTO> get(Pageable pageable) {
        List<ReviewModel> list = reviewRepository.findAll();
        return new PageImpl<>(getListDto(list), pageable, list.size());
    };


    public ReviewDTO getObjectDto(ReviewModel object) {
        ReviewDTO dto = new ReviewDTO();
        Optional<AccountModel> opAccount = accountRepository.findById(object.getUserId());
        if (!opAccount.isPresent()) {
            throw new EntityNotFoundException("User Id not found!");
        }
        dto.setNameOfCustomer(opAccount.get().getFullname());
        BeanUtils.copyProperties(object, dto);
        return dto;
    };

    public List<ReviewDTO> getListDto(List<ReviewModel> list) {
        List<ReviewDTO> listDto = new ArrayList<>();
        for (ReviewModel object : list) {
            ReviewDTO dto = getObjectDto(object);
            listDto.add(dto);
        }
        return listDto;
    };

}

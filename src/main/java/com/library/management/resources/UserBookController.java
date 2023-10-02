package com.library.management.resources;

import com.library.management.domain.dto.*;
import com.library.management.domain.entity.LibraryBook;
import com.library.management.domain.entity.UserBook;
import com.library.management.services.LibraryBookService;
import com.library.management.services.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
public class UserBookController {

    private UserBookService userBookService;

    private LibraryBookService libraryBookService;

    @Autowired
    public UserBookController(UserBookService userBookService, LibraryBookService libraryBookService) {
        this.userBookService = userBookService;
        this.libraryBookService = libraryBookService;
    }

    @PostMapping(path = "/checkout")
    public @ResponseBody String checkoutBook(@RequestBody UserBookDTO userBookDTO) {
        System.out.println(userBookDTO);
        for(int bookId : userBookDTO.getBookIds()) {
            int borrowedQuantity = userBookDTO.getBorrowedQuantities().get(userBookDTO.getBookIds().indexOf(bookId));
            UserBook userBook = new UserBook(userBookDTO.getUserId(), bookId, borrowedQuantity, userBookDTO.getBorrowedStatus(), new Date());
            userBookService.borrowBook(userBook);
        }
        return "Success";
    }

    @GetMapping(path = "/getCheckedBooks/userId={userId}")
    public @ResponseBody CheckedBooksDTO getCheckedBooks(@PathVariable Integer userId) {
        List<UserBook> userBookList = userBookService.findAllBorrowedBooks(userId);
        CheckedBooksDTO checkedBooksDTO = new CheckedBooksDTO();
        for(UserBook userBook : userBookList) {
            LibraryBook libraryBook = libraryBookService.getBookById(userBook.getBookId());
            Date returnDate = new Date(userBook.getBorrowedDate().getTime());
            returnDate.setTime(userBook.getBorrowedDate().getTime() + 30L * 1000 * 60 * 60 * 24);
            checkedBooksDTO.getCheckedBookDTOs().add(new CheckedBookDTO(libraryBook, userBook.getBorrowedQuantity(), userBook.getBorrowedDate(), returnDate));
            System.out.println(userBook.getBorrowedDate() + " " + returnDate.getDate());
        }
        return checkedBooksDTO;
    }

    @PostMapping(path = "/returnBooks/userId={userId}")
    public @ResponseBody ReturnedBooksDTO returnBooks(@PathVariable Integer userId, @RequestBody ReturnedBooksDTO returnedBooksDTO) {
        for(ReturnedBookDTO returnedBookDTO : returnedBooksDTO.getReturnedBookDTOList()) {
            UserBook userBook = userBookService.findByBookIdAndUserId(returnedBookDTO.getBookId(), userId);
            System.out.println(userBook);
            userBookService.returnBook(userBook, returnedBookDTO.getReturnedQuantity());
        }
        return returnedBooksDTO;
    }
}

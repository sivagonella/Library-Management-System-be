package com.library.management.resources;

import com.library.management.domain.dto.CheckedBooksDTO;
import com.library.management.domain.dto.UserBookDTO;
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
            UserBook userBook = new UserBook(userBookDTO.getUserId(), bookId, borrowedQuantity, userBookDTO.getBorrowedStatus(), userBookDTO.getBorrowedDate());
            userBookService.borrowBook(userBook);
        }
        return "Success";
    }

    @GetMapping(path = "/getCheckedBooks/userId={userId}")
    public @ResponseBody CheckedBooksDTO getCheckedBooks(@PathVariable Integer userId) {
        List<UserBook> userBookList = userBookService.findAllBorrowedBooks(userId);
        List<LibraryBook> borrowedBooks = new ArrayList<>();
        List<Integer> borrowedQuantities = new ArrayList<>();
        List<Date> borrowedDates = new ArrayList<>();
        for(int i = 0; i < userBookList.size(); i++) {
            borrowedBooks.add(libraryBookService.getBookById(userBookList.get(i).getBookId()));
            borrowedQuantities.add(userBookList.get(i).getBorrowedQuantity());
            borrowedDates.add(userBookList.get(i).getDate());
        }
        return new CheckedBooksDTO(borrowedBooks, borrowedQuantities, borrowedDates);
    }
}

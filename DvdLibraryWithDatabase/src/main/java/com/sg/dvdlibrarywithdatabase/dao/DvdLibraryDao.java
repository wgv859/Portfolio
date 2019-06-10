/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarywithdatabase.dao;

import com.sg.dvdlibrarywithdatabase.model.Dvd;
import java.util.List;

/**
 *
 * @author wgv85
 */
public interface DvdLibraryDao {
    
    Dvd addDvd(Dvd dvd) throws DvdLibraryDaoException;
    
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;
    
    Dvd getDvdById(int dvdId) throws DvdLibraryDaoException;
    
    void removeDvdLibrary(int dvdId) throws DvdLibraryDaoException;
    
    void editDvdLibrary(Dvd dvd) throws DvdLibraryDaoException;
    
    List<Dvd> getDvdByMpaa(String mpaaRating) throws DvdLibraryDaoException;
    
    List<Dvd> getDvdByStudio(String studio) throws DvdLibraryDaoException;
    
    List<Dvd> getDvdByDirectorName(String directorName) throws DvdLibraryDaoException;
    
}

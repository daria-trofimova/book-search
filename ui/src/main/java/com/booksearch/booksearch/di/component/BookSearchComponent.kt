package com.booksearch.booksearch.di.component

import com.booksearch.domain.interactor.BooksInteractor
import com.booksearch.domain.interactor.BooksInteractorImpl
import com.booksearch.domain.repository.BooksRepository
import com.booksearch.booksearch.fragment.book.search.BookSearchViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class BookSearchScope

@BookSearchScope
@Component(dependencies = [AppComponent::class], modules = [InteractorsModule::class])
interface BookSearchComponent {

    fun inject(bookSearchViewModel: BookSearchViewModel)
}

@Module
class InteractorsModule {

    @Provides
    @BookSearchScope
    fun provideBooksInteractor(
        booksRepository: BooksRepository
    ): BooksInteractor {
        return BooksInteractorImpl(booksRepository)
    }
}
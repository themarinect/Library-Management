--drop database [LibraryManagementSystems]

create database [LibraryManagementSystems] ON PRIMARY 
( 
	NAME = N'[LibraryManagementSystem]', 
	FILENAME = N'E:\LibraryManagementSystems.mdf' , 
	SIZE = 2048KB , 
	MAXSIZE = UNLIMITED, 
	FILEGROWTH = 1024KB )
LOG ON 
( 
	NAME = N'H2MS_log', 
	FILENAME = N'E:\LibraryManagementSystems.ldf' , 
	SIZE = 1024KB , 
	MAXSIZE = 2048GB , 
	FILEGROWTH = 10%
)
GO
use [LibraryManagementSystems]


--create database LibraryManagementSystems
--go
--use LibraryManagementSystems

create table Employee(

	EmployeeID int identity(1,1) primary key,
	e_Name nvarchar(50),
	Address nvarchar(50),
	PhoneNumber varchar(50),
	Department nvarchar(50),
	Birthday datetime,
	Email varchar(40),
	Gender bit

);


create table tblUsers
(
	U_ID int not null identity(1,1) primary key,
	U_Name varchar(30) not null,
	UserName varchar(30) not null,
	Password varchar(32) not null,
	Email varchar(30),
	BirthDate datetime,
	Gender bit,
	Phone varchar(20)
)

create table Book(

	bookId int identity(1,1) primary key,
	CallNumber varchar(50) unique,
	ISBN varchar(50) unique,
	Title nvarchar(100),
	NumOfCopies int,
	NameAuthor nvarchar(50),
	PublishHouse nvarchar(50)
	
	);
create table Borrow(

	borrowId int identity(1,1) primary key,
	EmployeeID int references Employee(EmployeeID),
	startDate datetime,
	dueDate   datetime,
	realDate datetime,
	fine int,
	borrowStatus bit,
);

create table BorrowDetail(

	borrowId int references Borrow(borrowId),
	bookId int references Book(bookId),
   	primary key(borrowId,bookId),
	
	

);
create table [Subject](

	subjectid int identity(1,1) primary key,
	subjectname nvarchar(50)

);

insert into [Subject] values('Book Program')
insert into [Subject] values('Book Relax')
insert into [Subject] values('Book Sport');


INSERT INTO tblUsers([U_Name], [UserName], [Password], [Email], [BirthDate], [Gender], [Phone]) 
VALUES ('Vu Duc Ha', 'vuducha', 'fcea920f7412b5da7be0cf42b8c93759', 'tonytruong1986@yahoo.com', '10/25/1986 12:00:00 AM', 1, '0974560286')


insert into Employee values('Nguyen Van Hau','Nam Dinh','0979913986','Ke Toan','1/1/1986','nvhau@yahoo.com',1)

insert into Employee values('Do Manh Cuong','Ninh Binh','0979913987','Ke Toan','1/1/1987','dmcuong@yahoo.com',1)


insert into Book(CallNumber,ISBN,Title,NumOfCopies,NameAuthor,PublishHouse) values('DC-A-001','001-0001','Distributed Computing Java','40',1,'Aptech')
insert into Book(CallNumber,ISBN,Title,NumOfCopies,NameAuthor,PublishHouse) values('GA-A-002','001-0002','Guide Advanced Java','30',1,'Aptech')

select *from book
select *from tblUsers

select *from Borrow

select *from BorrowDetail
insert into BorrowDetail values(2,1)

select *from BorrowDetail
delete from BorrowDetail
insert into Borrow values(1,'11/16/2009','11/21/2009','11/25/2009',0,1)

alter table Book add  BookContinue int 
alter table Borrow drop column BookContinue 

select *from Book

update Book set PublishHouse = 'Aptech1' where bookid = 2

update Book set BookContinue = 40 where Bookid = 1

select *from book
update book set BookContinue = 0 where bookid = 1

select *from Employee

select *from tblUsers

update Borrow set borrowStatus = 0 where borrowid = 2

create Proc tblBorrow_SearchByEmployeeNameAndDate
@str nvarchar(250),@StartDate datetime,@EndDate datetime
as
select borrowID,startDate,dueDate,realDate,fine,borrowStatus,EmployeeID from Borrow where EmployeeID in (select EmployeeID from Employee where e_Name like '%'+@str+'%') and startDate = @StartDate and duedate = @EndDate
go

create Proc tblBorow_SearchByDate
@StartDate datetime,@EndDate datetime
as
select borrowID,startDate,dueDate,realDate,fine,borrowStatus,EmployeeID from Borrow where startDate = @StartDate and duedate= @EndDate
go

create Proc tblBorrow_SearchByStartDate
@StartDate datetime
as
select borrowID,startDate,dueDate,realDate,fine,borrowStatus,EmployeeID from Borrow where startDate = @StartDate
go

create Proc tblBorrow_SearchByCustomerName
@str nvarchar(250)
as
begin
select borrowID,startDate,dueDate,realDate,fine,borrowStatus,EmployeeID from Borrow where EmployeeID in (select EmployeeID from Employee where e_Name like '%'+@str+'%') 
end

create proc tblBorrow_SearchByCustomerNameAndStartDate
@str nvarchar(250),@StartDate datetime
as
BEGIN
select borrowID,startDate,dueDate,realDate,fine,borrowStatus,EmployeeID from Borrow where EmployeeID in (select EmployeeID from Employee where e_Name like '%'+@str+'%') and startDate = @StartDate
END

create proc tblBorrow_SearchByCustomerNameAndEndDate
@str nvarchar(250),@EndDate datetime
as
BEGIN
select borrowID,startDate,dueDate,realDate,fine,borrowStatus,EmployeeID from Borrow where EmployeeID in (select EmployeeID from Employee where e_Name like '%'+@str+'%') and dueDate = @EndDate
END




create Proc [dbo].[tblUsers_SearchByName]
@str nvarchar(250)
As
BEGIN
	SET NOCOUNT ON;
	Select Username,U_Name,Email,BirthDate,Gender,Phone from tblUsers where Username like '%'+@str+'%' order by Username
END




create Proc [dbo].[Employee_SearchByName]
@str nvarchar(250)
As
BEGIN
	SET NOCOUNT ON;
	Select EmployeeID,e_Name,Address,PhoneNumber,Department,Birthday,Email,Gender  from Employee where e_Name like '%'+@str+'%' order by e_Name
END


create Proc tblBook_SearchByISBN 
@ISBN datetime
as
select  bookID,CallNumber,Isbn,Title,NumOfCopies,NameAuthor,PublishHouse,bookContinue from Book where isbn = @ISBN


create Proc tblBook_SearchByTitleISBN 
@Title varchar(50),@ISBN varchar(50)
as
select bookID,CallNumber,Isbn,Title,NumOfCopies,NameAuthor,PublishHouse,bookContinue from Book where Title like '%'+@Title+'%'and isbn= @ISBN
go

create Proc tblBook_SearchByCallnumber
@Callnumber varchar(50)
as
select  bookID,CallNumber,Isbn,Title,NumOfCopies,NameAuthor,PublishHouse,bookContinue from Book where CallNumber = @Callnumber


create Proc [dbo].[tblBook_SearchByCallnumber1]--Tim trong addbook
@Callnumber varchar(50)
as
select  bookID,CallNumber,Isbn,Title,NumOfCopies,NameAuthor,bookContinue from Book where CallNumber = @Callnumber





create Proc tblBook_SearchByCallnumberAndISBN 
@Callnumber varchar(50),@ISBN varchar(50)
as
select BookID,CallNumber,Isbn,Title,NumOfCopies,NameAuthor,PublishHouse,bookContinue from Book where CallNumber = @Callnumber and isbn= @ISBN
go


create Proc tblBook_SearchByCallNumberAndtitle 
@Callnumber varchar(50),@Title varchar(50)
as
select BookID,CallNumber,Isbn,Title,NumOfCopies,NameAuthor,PublishHouse,bookContinue from Book where CallNumber = @Callnumber and Title like '%'+@Title+'%'
go


create Proc tblBook_SearchByCallAndtitleAndIS 
@Callnumber varchar(50),@ISBN varchar(50),@Title varchar(50)
as
select BookID,CallNumber,Isbn,Title,NumOfCopies,NameAuthor,PublishHouse,bookContinue from Book where CallNumber = @Callnumber and isbn= @ISBN and Title like '%'+@Title+'%'
go


CREATE PROCEDURE [dbo].[sp_getcallnumber]
@callnumber varchar(50)= NULL OUTPUT
AS


SELECT @callnumber = CallNumber from Book Where bookId = (SELECT MAX(bookId) from Book)

create procedure [dbo].[sp_getSubject]
as
select subjectname from [Subject]



CREATE procedure [dbo].[sp_getisbn]

@subid varchar(50),
@isbn varchar(50)  = NULL OUTPUT

AS
BEGIN
select @subid = LTRIM(RTRIM(@subid))+'%'
Select @isbn=ISBN from Book Where bookId= (select MAX(bookId) from Book where ISBN LIKE @subid)
END


create procedure [dbo].[sp_subjectid]
@subjectname nvarchar(50),
@subjectid int = null OUTPUT
as
select @subjectid=subjectid from [Subject] where subjectname = @subjectname
GO

create Proc sp_getcallnumberedit
@bookID varchar(50),
@callnumber varchar(50)= NULL OUTPUT
AS
SELECT @callnumber = CallNumber from Book Where bookId = @bookID

create  procedure [dbo].[sp_getisbnedit]

@subid varchar(50), 
@bookID varchar(30),
@isbn varchar(50)  = NULL OUTPUT

AS
BEGIN
select @subid = LTRIM(RTRIM(@subid))+'%'
Select @isbn=ISBN from Book Where bookId= @bookID and ISBN LIKE @subid 
END

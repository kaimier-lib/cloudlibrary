package com.ybvtc.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName book
 */
@TableName(value ="book")
@Data
public class Book implements Serializable {
    /**
     * 图书编号
     */
    @TableId(value = "book_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图书名称
     */
    @TableField(value = "book_name")
    private String name;

    /**
     * 图书标准ISBN编号
     */
    @TableField(value = "book_isbn")
    private String isbn;

    /**
     * 图书出版社
     */
    @TableField(value = "book_press")
    private String press;

    /**
     * 图书作者
     */
    @TableField(value = "book_author")
    private String author;

    /**
     * 图书页数
     */
    @TableField(value = "book_pagination")
    private Integer pagination;

    /**
     * 图书价格
     */
    @TableField(value = "book_price")
    private Double price;

    /**
     * 图书上架时间
     */
    @TableField(value = "book_uploadtime")
    private String uploadtime;

    /**
     * 图书状态（0：可借阅，1:已借阅，2：归还中，3：已下架）
     */
    @TableField(value = "book_status")
    private String status;

    /**
     * 图书借阅人
     */
    @TableField(value = "book_borrower")
    private String borrower;

    /**
     * 图书借阅时间
     */
    @TableField(value = "book_borrowtime")
    private String borrowtime;

    /**
     * 图书预计归还时间
     */
    @TableField(value = "book_returntime")
    private String returntime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Book other = (Book) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIsbn() == null ? other.getIsbn() == null : this.getIsbn().equals(other.getIsbn()))
            && (this.getPress() == null ? other.getPress() == null : this.getPress().equals(other.getPress()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getPagination() == null ? other.getPagination() == null : this.getPagination().equals(other.getPagination()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getUploadtime() == null ? other.getUploadtime() == null : this.getUploadtime().equals(other.getUploadtime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getBorrower() == null ? other.getBorrower() == null : this.getBorrower().equals(other.getBorrower()))
            && (this.getBorrowtime() == null ? other.getBorrowtime() == null : this.getBorrowtime().equals(other.getBorrowtime()))
            && (this.getReturntime() == null ? other.getReturntime() == null : this.getReturntime().equals(other.getReturntime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIsbn() == null) ? 0 : getIsbn().hashCode());
        result = prime * result + ((getPress() == null) ? 0 : getPress().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getPagination() == null) ? 0 : getPagination().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getUploadtime() == null) ? 0 : getUploadtime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBorrower() == null) ? 0 : getBorrower().hashCode());
        result = prime * result + ((getBorrowtime() == null) ? 0 : getBorrowtime().hashCode());
        result = prime * result + ((getReturntime() == null) ? 0 : getReturntime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", isbn=").append(isbn);
        sb.append(", press=").append(press);
        sb.append(", author=").append(author);
        sb.append(", pagination=").append(pagination);
        sb.append(", price=").append(price);
        sb.append(", uploadtime=").append(uploadtime);
        sb.append(", status=").append(status);
        sb.append(", borrower=").append(borrower);
        sb.append(", borrowtime=").append(borrowtime);
        sb.append(", returntime=").append(returntime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
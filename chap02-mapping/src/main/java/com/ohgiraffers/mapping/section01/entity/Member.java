package com.ohgiraffers.mapping.section01.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "entityMember")
@Table(name = "tbl_member")
@Access(AccessType.FIELD)   // 클래스 레벨에 설정 가능하며 모든 필드를 대상으로 적용하겠다는 의미 -> default
public class Member {

    /* 영속성 컨텍스트를 관리하기 위해서는 Id 어노테이션이 꼭 필요함
     * Id라는 속성이 어느 부분에는 꼭 붙어있어야함 */
    @Id
    @Column(name = "member_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberNo;

    @Access(AccessType.FIELD)   // 필드 레벨에 설정 가능하며 해당 필드를 대상으로 적용하겠다는 의미
    @Column(
            name = "member_id", unique = true,
            nullable = false, columnDefinition = "varchar(10)"
    )
    private String memberId;
    @Column(name = "member_pwd", nullable = false)
    private String memberPwd;
    @Column(name = "member_name")
    private String memberName;
    /* @Transient : 해당 필드를 데이터베이스 테이블의 컬럼에 매핑하지 않는다는 것을 나타낸다.
     * 이 어노테이션을 사용하면 해당 필드는 데이터베이스에 저장되거나 검색되지 않는다.*/
//    @Transient
    @Column(name = "phone")
    private String phone;
    @Column(name = "address", length = 900)
    private String address;
    @Column(name = "enroll_date")
    private LocalDateTime enrollDate;
    @Column(name = "member_role")
    /* Enum 타입에 대한 설정을 해줄 수 있는 어노테이션 */
    @Enumerated(EnumType.ORDINAL)
    private MemberRole memberRole;

    /* ORDINAL : 숫자로 다루게 돼서
     * ROLE_MEMBER : 1번
     * ROLE_ADMIN : 2번으로 됨 */

    @Column(name = "status", columnDefinition = "char(1) default 'Y'")
    private String status;
    protected Member() {}
    public Member(
            String memberId, String memberPwd, String memberName,
            String phone, String address, LocalDateTime enrollDate,
            MemberRole memberRole, String status
    ) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.phone = phone;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }

    @Access(AccessType.PROPERTY)
    public String getMemberName() {
        System.out.println("getMemberName 메소드를 통한 Access 확인");
        return memberName + "님";
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}

'use strict';

describe('ngCart module', function() {
    beforeEach(module('ngCart'));


    describe('value - version', function() {
        it('should return current version', inject(function(version) {
            expect(version).toEqual('1.0.0');
        }));
    });


    describe('CartController', function() {

        var $controller;

        beforeEach(inject(function(_$controller_){
            // The injector unwraps the underscores (_) from around the parameter names when matching
            $controller = _$controller_;
        }));

        describe('$scope.ngCart', function() {

            var $scope;
            var controller;

            function addItem(id, name, price, quantity, data){
                $scope.ngCart.addItem(id, name, price, quantity, data);
            }

            beforeEach(function() {
                $scope = {};
                controller = $controller('CartController', {$scope: $scope});

            });

            it('sets instance of ngCart to scope', function() {
                expect(typeof $scope.ngCart).toEqual('object');
            });


            it('should be able to add an item', function() {
                addItem(1, 'Test Item', 10, 2);
                expect($scope.ngCart.getItems().length).toEqual(1);
            });


             it('should be able to empty', function() {
                $scope.ngCart.empty();
                expect($scope.ngCart.getItems().length).toEqual(0);
            });

            it('should be able to show isEmpty', function() {
                $scope.ngCart.empty();
                expect($scope.ngCart.isEmpty()).toEqual(true);
            });


            describe('ngCart', function() {


                beforeEach(function(){

                    $scope.ngCart.setTaxRate(7.5);
                    $scope.ngCart.setShipping(12.50);
                    addItem(1, 'Work boots', 189.99, 1);
                    addItem(2, 'Hockey gloves', 85, 2);
                    addItem('cpBow', 'Compound bow', 499.95, 1);
                });



                it('tax should be set', function() {
                    expect($scope.ngCart.getTaxRate()).toEqual(7.5);
                });

                it('shipping should be set', function() {
                    expect($scope.ngCart.getShipping()).toEqual(12.50);
                });

                it('tax charge should be ', function() {
                    expect($scope.ngCart.getTax()).toEqual(64.5);
                });

                it('count items in total', function() {
                    expect($scope.ngCart.getTotalItems()).toEqual(4);
                });

                it('count unique items in cart', function() {
                    expect($scope.ngCart.getTotalUniqueItems()).toEqual(3);
                });


                it('check getItems has correct number of items', function() {
                    expect($scope.ngCart.getItems().length).toEqual(3);
                });

                it('Have correct getSubTotal', function() {
                    expect($scope.ngCart.getSubTotal()).toEqual(859.94);
                });
                
                it('should be able to show isEmpty correctly as false', function() {
                    expect($scope.ngCart.isEmpty()).toEqual(false);
                });

                it('Have correct totalCost', function() {
                    expect($scope.ngCart.totalCost()).toEqual(936.94);
                });


                it('find item by id (by int) ', function() {
                    expect($scope.ngCart.getItemById(2).getName()).toEqual('Hockey gloves');
                });


                it('find item by id (by string) ', function() {
                    expect($scope.ngCart.getItemById('cpBow').getName()).toEqual('Compound bow');
                });


                it('remove item by ID', function() {
                    $scope.ngCart.removeItemById('cpBow');
                    expect($scope.ngCart.getItemById('cpBow')).toEqual(false);
                    expect($scope.ngCart.getTotalUniqueItems()).toEqual(2);
                });


                it('remove item by ID', function() {
                    $scope.ngCart.removeItemById('cpBow');
                    expect($scope.ngCart.getItemById('cpBow')).toEqual(false);
                });

                it('should create an object', function() {
                    var obj =  $scope.ngCart.toObject();
                    expect(obj.shipping).toEqual( 12.50 );
                    expect(obj.tax).toEqual( 64.5 );
                    expect(obj.taxRate).toEqual( 7.5 );
                    expect(obj.subTotal).toEqual( 859.94 );
                    expect(obj.totalCost).toEqual( 936.94 );
                    expect(obj.items.length).toEqual( 3 );
                });


            });

            describe('ngCartItem', function() {

                var ngCartItem;

                beforeEach(function(){
                    addItem('lRope', 'Lariat rope', 39.99);
                    ngCartItem = $scope.ngCart.getItemById('lRope');
                });


                it('should have correct Name', function() {
                    expect(ngCartItem.getName()).toEqual('Lariat rope');
                });

                it('should default quantity to 1', function() {
                    expect(ngCartItem.getQuantity()).toEqual(1);
                });

                it('should update quantity', function() {
                    expect(ngCartItem.getName()).toEqual('Lariat rope');
                });

                it('should absolutely update quantity', function() {
                    expect(ngCartItem.getQuantity()).toEqual(1);
                    ngCartItem.setQuantity(5);
                    expect(ngCartItem.getQuantity()).toEqual(5);
                });

                it('should relatively update quantity', function() {
                    expect(ngCartItem.getQuantity()).toEqual(1);
                    ngCartItem.setQuantity(1, true);
                    expect(ngCartItem.getQuantity()).toEqual(2);
                });


                it('should get total', function() {
                    expect(ngCartItem.getTotal()).toEqual(39.99);
                });

                it('should update total after quantity change', function() {
                    ngCartItem.setQuantity(1, true);
                    expect(ngCartItem.getTotal()).toEqual( 79.98 );
                });


                it('should create an object', function() {
                    var obj = ngCartItem.toObject();
                    expect(obj.id).toEqual( 'lRope' );
                    expect(obj.name).toEqual( 'Lariat rope' );
                    expect(obj.price).toEqual( 39.99 );
                    expect(obj.quantity).toEqual( 1 );
                    expect(obj.data).toEqual( null );
                    expect(obj.total).toEqual( 39.99 );
                });


            })

        })
    });





    describe('ngCartItem', function() {

        //var ngCartItem;
        //
        //beforeEach(inject(function(_ngCartItem_){
        //    // The injector unwraps the underscores (_) from around the parameter names when matching
        //
        //
        //    var $rootScope = {};
        //     ngCartItem = _ngCartItem_('ngCartItem', { $rootScope: $rootScope });
        //
        //}));
        //
        //describe('$scope.ngCart', function() {
        //
        //    it('sets instance of ngCart to scope', function() {
        //       console.log( ngCartItem);
        //        expect(ngCartItem.getQuantity()).toEqual(1);
        //    });
        //
        //});
    });

    describe('ngCart', function() {

        //var $service;
        //
        //beforeEach(inject(function(_ngCartService_){
        //    // The injector unwraps the underscores (_) from around the parameter names when matching
        //    $service = _ngCartService_;
        //}));
        //
        //describe('ngCart.init', function() {
        //
        //    console.log ($service)
        //    it('sets instance of ngCart to scope', function() {
        //        var $scope = {};
        //        //var service = $service('ngCart', { $scope: $scope });
        //
        //        //expect('object').toEqual('object');
        //    });
        //
        //});
    });





});
